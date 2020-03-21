package cn.sc.reflection.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射的工具方法
 */

public class ReflectionUtils {

    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param clazz
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index) {
        //得到父类的泛型类型，TYPE
        Type genericSuperclass = clazz.getGenericSuperclass();
        //TYPE的实现类，ParameterizedType，带参数的类型，可以获取参数列表
        if (genericSuperclass instanceof ParameterizedType) {
            //强转
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //获取泛型的参数列表
            Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
            if (actualTypeArgs != null && actualTypeArgs.length > 0 &&
                    index <= actualTypeArgs.length - 1) {

                //获取指定的泛型参数
                Type arg = actualTypeArgs[index];
                if (arg instanceof Class) {
                    //返回泛型参数的类型
                    return (Class) arg;
                }
            }
        }


        return null;
    }

    /**
     * 执行类的（私有）方法，若果该类中没有，就到父类中去找（私有）方法
     *
     * @param obj        类对象
     * @param methodName 方法名
     * @param args       参数列表
     * @return
     */
    public Object invokeDeclaredMethod(Object obj, String methodName, Object[]... args) {
        Method method = getMethod(obj.getClass(), methodName, getparameterTypes(args));
        if (method != null) {
            method.setAccessible(true);
            try {
                return method.invoke(obj, args);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public Field getFiled(Class clazz, String filedName) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(filedName);
                return field;
            } catch (NoSuchFieldException e) {

            }
        }

        return null;
    }

    /**
     * 获取类的（私有）方法，或者继承自父类的（私有）方法，直到Object类为止
     *
     * @param clazz          全类名
     * @param methodName     方法名
     * @param parameterTypes 参数类型列表
     * @return
     */
    public Method getMethod(Class clazz, String methodName, Class... parameterTypes) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * @param className：全类名
     * @param methodName：方法名
     * @param args：参数列表
     * @return
     * @throws Exception
     */
    public static Object invoke(String className, String methodName, Object... args) throws Exception {
        Object obj = Class.forName(className).newInstance();
        return invoke(obj, methodName, args);
    }

    /**
     * 工具方法
     *
     * @param object：对象
     * @param methodName：方法名
     * @param args：参数列表
     * @return
     * @throws Exception
     */
    public static Object invoke(Object object, String methodName, Object... args) throws Exception {

//        Class [] parameterTypes = new Class[args.length];
//        for(int i =0;i<args.length;i++){
//            parameterTypes[i] = args[i].getClass();
//            System.out.println(parameterTypes[i]);
//        }
        Class[] parameterTypes = getparameterTypes(args);
        Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
        return method.invoke(object, args);
    }

    /**
     * 接受参数列表返回参数类型列表
     *
     * @param args 参数列表
     * @return
     */
    public static Class[] getparameterTypes(Object... args) {
        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }

}
