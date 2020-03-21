package function;

/**
 * @Author: Kuroneko
 * @Date: created in 21:31 2018/4/8
 * @Description:
 */
@FunctionalInterface
public interface MyFunTR<T,R> {
    public R getValue(T t1,T t2);
}
