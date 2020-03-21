package run;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: Kuroneko
 * @Date: created in 21:41 2018/4/8
 * @Description:
 *  * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 */
public class Java8CoreInterface {

    /**
     * Consumer<T> : 消费型接口
     */
    @Test
    public void core1(){
        happy(10000.0,(m)-> System.out.println("消费了"+m+"元"));
    }

    public void happy(Double money, Consumer<Double> con){
        con.accept(money);
    }


    /**
     * Supplier<T> : 供给型接口
     */
    @Test
    public void core2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.stream()
                .forEach((e)-> System.out.println(e));
    }

    /**
     * 需求，产生指定个数的整数，放入集合中
     */
    private List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }

        return list;
    }

    /**
     * Function<T, R> : 函数型接口
     */
    @Test
    public void core3(){
        System.out.println(strHandler("abcde",(x)->x.toUpperCase()));
    }

    private String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);

    }

    /**
     * Predicate<T> : 断言型接口
     */
    @Test
    public void core4(){
        Boolean test = notEmpty("test", (x) -> "".equals(x));
        System.out.println(test);

    }

    private Boolean notEmpty(String str, Predicate<String> predicate){
        return predicate.test(str);
    }
}
