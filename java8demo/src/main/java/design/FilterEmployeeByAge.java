package design;

import domain.Employee;

/**
 * @Author: Kuroneko
 * @Date: created in 22:47 2018/3/27
 * @Description:
 */
public class FilterEmployeeByAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
