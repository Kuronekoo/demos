package run;

import domain.Employee;
import function.MyFunTR;
import function.MyFunc;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author Kuroneko
 * @Date: created in 23:16 2018/3/27
 * @Description:
 */
public class LearnLambda {
    /**
     *  * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
     * 						    箭头操作符将 Lambda 表达式拆分成两部分：
     *
     * 左侧：Lambda 表达式的参数列表
     * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
     *
     *
     *  * 二、Lambda 表达式需要“函数式接口”的支持
     * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
     * 			 可以检查是否是函数式接口
     *
     *
     *
     *  * 语法格式一：无参数，无返回值
     * 		() -> System.out.println("Hello Lambda!");
     * 	 * 语法格式二：有一个参数，并且无返回值
     * 		(x) -> System.out.println(x)
     */

    /**
     *      *  * 语法格式一：无参数，无返回值
     * 		() -> System.out.println("Hello Lambda!");
     */
    @Test
    public void testVoidNoParam(){
//        jdk 1.7 前，在同级下的变量要在匿名内部类中使用必须是 final，在java8中可以省略，但是本质还是final，比如
//        不能在匿名内部类里面使用num++
        int num = 0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello runnable"+num);
            }
        };

        r.run();

        System.out.println("----------------");

        Runnable rLambda = ()-> System.out.println("hello Lambda"+num);

        rLambda.run();
    }

    /**
     *  * 语法格式二：有一个参数，并且无返回值
     * 		(x) -> System.out.println(x)
     * 	 * 语法格式三：若只有一个参数，小括号可以省略不写
     * 		x -> System.out.println(x)
     */
    @Test
    public void testVoidParam(){
        Consumer<String> con = (x)-> System.out.println(x);
        Consumer<String> con1=x-> System.out.println(x);
        con.accept("hello");
        con1.accept("hello1");

    }

    /**
     *  * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句,需要使用大括号
     *		Comparator<Integer> com = (x, y) -> {
     *			System.out.println("函数式接口");
     *			return Integer.compare(x, y);
     *		};
     *	    * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
     * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
     * 	 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     * 		(Integer x, Integer y) -> Integer.compare(x, y);
     */

    List<Integer> integerList = Arrays.asList(3,2,1,5,3,8,0);

    @Test
    public void testReturnParam(){
//        Comparable<Integer> com = (x,y) -> {
//            System.out.println("hello");
//            return Integer.compare(x,y);
//    };
//
//    Comparable<Integer> com2 = (x,y)->Integer.compare(x,y);

    }

    /**

     */
    @Test
    public void LambdaExsice1(){

        Integer caculate = this.caculate(100, x -> x / 10);
        System.out.println(caculate);

    }

    private Integer caculate(Integer num, MyFunc myFunc){

        return myFunc.getValue(num);
    }


    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.9),
            new Employee("李四",35,4444.4),
            new Employee("王五",40,2222.2),
            new Employee("小六",65,3333.3),
            new Employee("田七",8,6666.6)
    );

    @Test
    public void lambdaExcise2(){
        Collections.sort(employees,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return   Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee employee:employees){
            System.out.println(employee);
        }

    }


    @Test
    public void lambdaExcie3(){

        Long aLong = caculateLong(1L, 2L, (x, y) -> x + y);
        System.out.println(aLong);


    }


    private Long caculateLong(Long x, Long y, MyFunTR<Long,Long> myFunTR){
        return myFunTR.getValue(x,y);
    }
}
