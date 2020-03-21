package run;

import domain.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * @Author: Kuroneko
 * @Date: created in 14:03 2018/4/9
 * @Description:
 *
 *  * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 *
 */
public class StreamAPI {

    /**
     * 创建流的四种方法
     */
    @Test
    public void createStream(){

        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        //串行流
        Stream<String> stream1 = list.stream();
        //并行流（效率更高）
        Stream<String> stream2 = list.parallelStream();

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream3 = stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream4 = Stream.of(1,2,3,4,5,6);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream5 = Stream.iterate(0, (x) -> x + 2);
        stream5.
                limit(10).
                forEach(System.out::println);

        //生成
        Stream<Double> stream6 = Stream.generate(Math::random);
        stream6.
                limit(2).
                forEach(System.out::println);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.9, Employee.Status.BUSY),
            new Employee("李四",35,4444.4, Employee.Status.FREE),
            new Employee("王五",40,2222.2, Employee.Status.VOCATION),
            new Employee("小六",65,3333.3, Employee.Status.FREE),
            new Employee("田七",8,6666.6, Employee.Status.BUSY)
    );

    /**
     * 流的中间操作
     * 多个 中间操作可以连接起来形成一个 流水线，除非流水
     * 线上触发终止操作，否则 中间操作不会执行任何的 处理！
     * 而在 终止操作时一次性全部 处理，称为“惰性求值”。
     *
     * 	  筛选与切片
     *    filter——接收 Lambda ， 从流中排除某些元素。
     *    limit——截断流，使其元素不超过给定数量。
     *   skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     *   distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素，可能需要重写对象的hashCode()和equals()方法
     */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void  IntermediateSteam(){
        //所有的中间操作不会做任何的处理
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() <= 35;
                })
                .limit(2)
                .skip(1)
                .distinct();
        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        employeeStream.forEach(System.out::println);

    }

    /**
     * 流的中间操作
     * 映射
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * map中的参数为<R> Stream<R> map(Function<? super T, ? extends R> mapper);
     *
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void  IntermediateSteam2(){
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
            strList.stream()
                    .map((str)->str.toUpperCase())
                    .forEach(System.out::println);
        System.out.println("---------------------------");
            employees.parallelStream()
                    .map(Employee::getName)
                    .forEach(System.out::println);
        System.out.println("---------------------------");
//           如果函数的返回值是一个stream，则map之后获取到的是很多个一个包含了很多个流的流
        Stream<Stream<Character>> streamStream = strList.stream()
                .map(StreamAPI::filterCharacter);
//        先foreach对获取每个小流，然后再对每个流里面的元素进行操作
        streamStream.forEach((sm)->{sm.forEach(System.out::println);});

        Stream<String> stream = strList.stream();
        System.out.println("---------------------------");


//       测试map
        Stream<String> steamTest = strList.stream()
                .map(StreamAPI::testMap);
        steamTest.forEach(System.out::println);
        System.out.println("---------------------------");

//        flatMap
        Stream<Character> characterStream = strList.stream()
                .flatMap(StreamAPI::filterCharacter);
        characterStream.forEach(System.out::println);


    }


    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    public static String testMap(String str){
        return "test";
    }

    /**
     * 流的中间操作
     * sorted()——自然排序，实现了comparable接口
     * sorted(Comparator com)——定制排序
     */
    @Test
    public void  IntermediateSteam3(){
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
//        String实现了comparable接口
        strList.stream()
                .sorted()
                .forEach(System.out::println);

        employees.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
    /**
     * 流的终止操作(终端操作)
     *
     allMatch——检查是否匹配所有元素
     anyMatch——检查是否至少匹配一个元素
     noneMatch——检查是否没有匹配的元素
     findFirst——返回第一个元素
     findAny——返回当前流中的任意元素
     count——返回流中元素的总个数
     max——返回流中最大值
     min——返回流中最小值
     */
    @Test
    public void  TerminalSteam(){
//      allMatch——检查是否匹配所有元素
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

//     anyMatch——检查是否至少匹配一个元素
        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

//     findFirst——返回第一个元素
//     noneMatch——检查是否没有匹配的元素
        boolean b2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        //Optional是一个容器，用来防止元素为空
        Optional<Employee> first = employees.stream()
                .findFirst();
        //orElse方法就是如果元素是空，就搞一个代替的
        int age = first.orElse(new Employee()).getAge();
        Employee employee = first.get();
        System.out.println(age);
        System.out.println(employee);


//      findAny——返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());



//             count——返回流中元素的总个数
        long count = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .count();
        System.out.println(count);

//             max——返回流中最大值
        Optional<Employee> max = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());


//        min——返回流中最小值
//        查询最小的工资
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());

    }

    /**
     * 流的终止操作(终端操作)
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void  TerminalSteam2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);

        System.out.println("----------------------------------------");

        Optional<Double> op = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());
    }

}
