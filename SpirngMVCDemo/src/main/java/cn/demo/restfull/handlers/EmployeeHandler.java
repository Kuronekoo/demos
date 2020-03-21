package cn.demo.restfull.handlers;

import cn.demo.restfull.beans.Department;
import cn.demo.restfull.beans.Employee;
import cn.demo.restfull.dao.DepartmentDao;
import cn.demo.restfull.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;



    @RequestMapping("/listEmps")
    public String list(Map<String,Object> map){
        System.out.println("listemps");
        map.put("employees",employeeDao.getAll());
        return "list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String,Object> map){

        System.out.println("input");

        Map<String,String> genders = new HashMap<String,String>();
        genders.put("1","Male");
        genders.put("0","Female");

        map.put("departments",departmentDao.getDepartments());
        map.put("genders",genders);
        map.put("employee",new Employee());

        return "input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee){
        System.out.println("saved" + employee);
        employeeDao.save(employee);
        return "redirect:/listEmps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("id  = " + id);
        employeeDao.delete(id);
        return "redirect:/listEmps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object> map){

        Map<String,String> genders = new HashMap<String,String>();
        genders.put("1","Male");
        genders.put("0","Female");
        map.put("genders",genders);
        map.put("employee",employeeDao.get(id));
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }


    @RequestMapping(value = "/emp" ,method = RequestMethod.PUT)
    public String put(Employee employee){
        System.out.println("put" + employee);
        employeeDao.save(employee);
        return "redirect:/listEmps";
    }


    @ModelAttribute
    public void getEmp(@RequestParam(value = "id",required = false) Integer id,Map<String,Object> map){

        if(id != null){
            System.out.println("getEmp ModelAttribute " );
            map.put("employee",employeeDao.get(id));
        }
    }
}
