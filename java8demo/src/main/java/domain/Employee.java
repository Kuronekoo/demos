package domain;


import lombok.*;

/**
 * @Author: Kuroneko
 * @Date: created in 22:28 2018/3/22
 * @Description:
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;
    private Status status;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee( int age) {
        this.age = age;
    }

    public enum Status{
        BUSY,
        FREE,
        VOCATION
    }



}
