package cn.sc.reflection.javaBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Dao<T,K> {
    public T get(K k){
        return null;
    }

    public void save(T t){}

    public Dao(){
        System.out.println(this);
        System.out.println(this.getClass());
        System.out.println(this.getClass().getGenericSuperclass());
        //得到父类的泛型类型，TYPE
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        //TYPE的实现类，ParameterizedType，带参数的类型，可以获取参数列表
        if(genericSuperclass instanceof ParameterizedType){
            //强转
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            System.out.println(parameterizedType);
            //获取泛型的参数列表
            Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
            System.out.println(Arrays.asList(actualTypeArgs));
            if(actualTypeArgs != null && actualTypeArgs.length > 0 ){
                for(Type type : actualTypeArgs){
                    System.out.println(type);
                }
            }

        }
    }
}
