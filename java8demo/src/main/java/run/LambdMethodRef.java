package run;

import domain.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: Kuroneko
 * @Date: created in 11:15 2018/4/9
 * @Description:
 *  * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①Lambda体中调用方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 */
public class LambdMethodRef {
    /**
     * 1. 对象的引用 :: 实例方法名
     */
    @Test
    public void test1(){

//      lambda
        PrintStream ps1 = System.out;
        Consumer<String> con1 = (x)->ps1.println(x);

//      方法引用
        PrintStream ps2 = System.out;
        Consumer<String> con2 = ps2::println;
//      方法引用
        Consumer<String> con3 = System.out::println;
        con3.accept("abcde");


        Employee employee = new Employee();
//      lambda
        Supplier<String> sup1 = () ->employee.getName();
        String name = sup1.get();

//      方法引用
        Supplier<Integer> sup2 = employee::getAge;
        Integer age = sup2.get();
        System.out.println(age);


    }

    /**
     * 类名 :: 静态方法名
     *
     */
    @Test
    public void test2(){
//       lambda
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);

//       方法引用
        Comparator<Integer> com2=Integer::compare;

    }

    /**
     * 类名 :: 实例方法名
     * 若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     */
    @Test
    public void test3(){
//        lambda
        BiPredicate<String,String> bp=(x,y)->x.equals(y);

//       方法引用
        BiPredicate<String,String> bp2=String::equals;

    }

    /**
     * 构造器引用，默认引用和函数式接口参数列表和返回值一样的方法
     */
    @Test
    public void test4(){
        Supplier<Employee> sup = ()->new Employee();

//        构造器引用,
        Supplier<Employee> sup2 = Employee::new;

        Function<Integer,Employee> fun = Employee::new;
        Employee emp = fun.apply(123);
        System.out.println(emp);

    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){
        Function<Integer,String[]> fun = (x)->new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);

        Function<Integer,String[]> fun2 = String[]::new;
        String[] apply2 = fun2.apply(20);
        System.out.println(apply2.length);


    }

}
