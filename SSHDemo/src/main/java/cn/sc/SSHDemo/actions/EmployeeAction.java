package cn.sc.SSHDemo.actions;

import cn.sc.SSHDemo.beans.EmployeeEntity;
import cn.sc.SSHDemo.service.DepartmentService;
import cn.sc.SSHDemo.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.Request;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Controller
@Scope("prototype")
public class EmployeeAction extends ActionSupport implements RequestAware ,ModelDriven<EmployeeEntity>,Preparable{
    @Resource(name="employeeService")
    private EmployeeService employeeService;

    @Resource(name="departmentService")
    private DepartmentService departmentService;

    /**
     * 删除或者修改的时候用来接收id
     */
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 展示员工列表
     * @return
     */
    public String list(){
        request.put("employees",employeeService.getAll());
        System.out.println("list");
        return "list";
    }



    /**
     * 删除一个员工，
     * 前台使用的是ajax的删除方法，通过使用out.print的方法返回值
     * @return
     * @throws IOException
     */
    public String delete() throws IOException {
        HttpServletResponse res = ServletActionContext.getResponse();
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");
        PrintWriter out = res.getWriter();
        try {
            employeeService.deleteById(id);
            out.print("1");//删除成功返回1

        } catch (Exception e) {
            e.printStackTrace();
            out.print("0");//发生异常返回0
        }finally {
            out.flush();
            out.close();
        }
        return NONE;
    }

    /**
     * 转到新增员工页面
     * @return
     */
    public String input(){
        request.put("departments",departmentService.getAll());//将部门信息放到请求域中
        return INPUT;
    }

    public void prepareInput(){
        if(id != null){
            employeeEntity = employeeService.getById(id);//如果带了Id说明是修改操作，从数据库中获取员工对象，放到值栈栈顶
        }
    }
    /**
     * 将员工存到数据库中
     * @return
     */
    public String save(){
//        System.out.println(employeeEntity);
        if (id == null){
            employeeEntity.setCreateTime(new Date(System.currentTimeMillis()));//id是null说明是新增对象，添加一个创建时间
        }
        employeeService.saveOrUpdate(employeeEntity);//将值栈栈顶的员工存到数据库中，只修改部分属性
        return SUCCESS;
    }

    /**
     * 为save方法准备对象
     */
    public void prepareSave(){
        if(id == null){
            employeeEntity = new EmployeeEntity();//id是null说明是新增对象，new一个新的空的对象
        }else {
            employeeEntity = employeeService.getById(id);//id存在说明是修改对象，从数据库中根据id获取一个员工对象放到栈顶
        }
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 对姓名做验证，检查在数据库中是否已存在同名
     * @return
     * @throws IOException
     */
    public String validateName() throws IOException {
        HttpServletResponse res = ServletActionContext.getResponse();
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");
        PrintWriter out = res.getWriter();
        try {
            boolean flag = employeeService.getByEmployeeName(name);
            if(flag){
                out.print("1");
            }else {
                out.print("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("error");
        }finally {
            out.flush();
            out.close();
        }
        System.out.println("test...");
        return "ajax_test";
    }

    private  Map<String,Object> request;
    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    private EmployeeEntity employeeEntity;

    /**
     * 将employeeEntity放到值栈的栈顶
     * @return
     */
    @Override
    public EmployeeEntity getModel() {
        return employeeEntity;
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare.....");
    }
}
