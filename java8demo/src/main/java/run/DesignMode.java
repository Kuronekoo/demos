package run;

import design.FilterEmployeeByAge;
import design.MyPredicate;
import domain.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Kuroneko
 * @Date: created in 22:40 2018/3/27
 * @Description:
 */
public class DesignMode {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.9),
            new Employee("李四",35,4444.4),
            new Employee("王五",40,2222.2),
            new Employee("小六",65,3333.3),
            new Employee("田七",8,6666.6)
    );

    /**
     * 优化方式一：策略设计模式
     */
    public List<Employee> filterEmployees(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> returnList = new ArrayList<>();
        for (Employee employee:list) {
            if(mp.test(employee)){
                returnList.add(employee);
            }
        }
        return returnList;
    }

    @Test
    public void test1(){
        List<Employee> employees = filterEmployees(this.employees, new FilterEmployeeByAge());
        System.out.println(employees);
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void test2(){
        List<Employee> employees = filterEmployees(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary()>=5000;
            }
        });

        System.out.println(employees);
    }


    /**
     * lambda表达式
     */
    @Test
    public void test3(){
        List<Employee> employees = filterEmployees(this.employees, (e) -> e.getSalary() > 5000);
        employees.forEach(System.out::println);
    }

    /**
     * stream API
     */
    @Test
    public void test4(){
        employees.stream()
                .filter((e)->e.getSalary()>=5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

    }
}
