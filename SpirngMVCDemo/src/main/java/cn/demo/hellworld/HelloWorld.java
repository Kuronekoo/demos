package cn.demo.hellworld;

import cn.demo.hellworld.beans.User;
import cn.demo.restfull.beans.Employee;
import cn.demo.restfull.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloWorld {
    public static final String SUCCESS = "success";


    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {

        if (id != null) {
            User user = new User("tom", "123", 12);
            System.out.println("数据库中的user : " + user);
            map.put("abc", user);
        }

    }


    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("zzzz") User user) {
        System.out.println("表单提交的user : " + user);
        return SUCCESS;
    }

    /**
     * 测试rest风格的请求
     *
     * @return
     */
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("test rest delete id = " + id);
        return "redirect:/listEmps";
    }

    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("testPathVariable: " + id);
        return SUCCESS;
    }

    @RequestMapping("/helloworld")
    public String hello() {
        System.out.println("hello world..");
        return "success";
    }

    @RequestMapping("/test_date")
    public void testDate(@RequestParam("user_date") String user_date){
        System.out.println("user_date = " + user_date);
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Employee testJson(){
        System.out.println("Testjson");
//        List<Employee> employees = new ArrayList<Employee>();
//        employees.add(new Employee());
        return new Employee();
    }
}
