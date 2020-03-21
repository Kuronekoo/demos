package cn.sc.reflection.demo;


import cn.sc.reflection.annotation.AgeValidator;
import cn.sc.reflection.javaBean.Person;
import cn.sc.reflection.javaBean.PersonDao;
import cn.sc.reflection.javaBean.Student;
import org.junit.Test;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class TestReflection {

    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     * @param clazz
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public  Class getSuperClassGenricType(Class clazz, int index){
        //得到父类的泛型类型，TYPE
        Type genericSuperclass = clazz.getGenericSuperclass();
        //TYPE的实现类，ParameterizedType，带参数的类型，可以获取参数列表
        if(genericSuperclass instanceof ParameterizedType){
            //强转
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //获取泛型的参数列表
            Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();
            if(actualTypeArgs !=null && actualTypeArgs.length>0 &&
                    index <= actualTypeArgs.length -1){

                //获取指定的泛型参数
                Type arg = actualTypeArgs[index];
                if (arg instanceof Class){
                    //返回泛型参数的类型
                    return (Class) arg;
                }
            }
        }


        return null;
    }


    @Test
    public void testGetSuperClassGenricType(){

        System.out.println(getSuperClassGenricType(PersonDao.class,0));
        System.out.println(getSuperClassGenricType(PersonDao.class,1));

    }


    /**
     * 测试通过反射操作注解
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public void testAnnotation() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class personClass = Class.forName("cn.sc.reflection.javaBean.Person");
        Object person = personClass.newInstance();
        Method setName = personClass.getDeclaredMethod("setAge", Integer.class);

        Integer age = 19;

        //获取方法上的指定注解，通过注解的Class来指定
        Annotation annotation = setName.getAnnotation(AgeValidator.class);

        if (annotation != null) {
            if (annotation instanceof AgeValidator) {
                //强转
                AgeValidator ageValidator = (AgeValidator) annotation;
                //验证是否满足注解的条件
                if (age < ageValidator.min() || age > ageValidator.max()) {
                    throw new RuntimeException("年龄不合法！");
                }
            }
        }

        setName.invoke(person, age);
        System.out.println(person);
    }


    /**
     * 测试反射获取构造器
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void testConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class personClass = Class.forName("cn.sc.reflection.javaBean.Person");
        //获取一个类的所有构造器
        Constructor[] constructors = personClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        //获取一个类的指定构造器，通过参数列表来区别
        Constructor constructor = personClass.getDeclaredConstructor(String.class);
        //通过newInstance方法来新建对象
        Object person = constructor.newInstance("Tom");

    }

    /**
     * 测试反射获取字段
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testFiled() throws NoSuchFieldException, IllegalAccessException {

        try {
            Class personClass = Class.forName("cn.sc.reflection.javaBean.Person");
            Field[] fields = personClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.getName());
            }

            //获取指定类的指定字段
            Field nameFiled = personClass.getDeclaredField("name");

            Person person = new Person();
            //如果字段是私有字段，设置该字段是可访问的
            nameFiled.setAccessible(true);
            //设置某个对象的该字段的值
            nameFiled.set(person, "Tom");
            //获取某个对象的该字段的值
            System.out.println(nameFiled.get(person));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试反射获取方法（私有方法）
     */
    @Test
    public void testInvokeDeclaredMethod() {
        Object student = new Student();

        invokeDeclaredMethod(student, "pMethod");
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

    /**
     * 测试getMethod方法
     */
    @Test
    public void testGetMethod() {

        Object student = new Student();
        Class studentClass = student.getClass();
        Method method = getMethod(studentClass, "sMethod");
        System.out.println(method);

        method = getMethod(studentClass, "pMethod");
        System.out.println(method);
    }


    /**
     * 获取类的（私有）方法，或者继承自父类的（私有）方法，直到Object类为止
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public Method getMethod(Class clazz, String methodName, Class... parameterTypes) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (NoSuchMethodException e) {
            }
        }
        return null;
    }

    /**
     * 测试执行私有方法
     */
    @Test
    public void testInvokePiravteMethod() {
        Object student = new Student();
        Class studentClass = student.getClass();
        try {
            Method method = studentClass.getDeclaredMethod("sMethod");
            //执行类中的私有方法，必须将该方法设置为可访问，否则会报错
            method.setAccessible(true);
            method.invoke(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试获取父类
     */
    @Test
    public void testSuperClass() {
        try {
            Class studentClass = Class.forName("reflection.Student");
            //获取类的父类的Class对象
            Class personClass = studentClass.getSuperclass();
            System.out.println(personClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试自己写的两个invoke方法
     *
     * @throws Exception
     */
    @Test
    public void testInvoke() throws Exception {
        Object person = new Person();

        invoke(person, "setName", "Tom");
        invoke("reflection.Person", "setName", "Tom");
    }

    /**
     * @param className：全类名
     * @param methodName：方法名
     * @param args：参数列表
     * @return
     * @throws Exception
     */
    public Object invoke(String className, String methodName, Object... args) throws Exception {
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
    public Object invoke(Object object, String methodName, Object... args) throws Exception {

        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
            System.out.println(parameterTypes[i]);
        }

        Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
        return method.invoke(object, args);
    }

    /**
     * 测试通过反射获取方法
     *
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception {

        Class<?> clazz = Class.forName("reflection.Person");

//        获取类自己申明的方法，包括私有的方法
//        getMethod获得所有的public方法，包括继承来的
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

//      获取指定的方法，通过方法名和参数列表来确定一个指定的方法
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        System.out.println("setname = " + setName);

        Object obj = clazz.newInstance();

        setName.invoke(obj, "Tom");


    }

    /**
     * 测试通过反射获取类加载器
     *
     * @throws ClassNotFoundException
     */

    @Test
    public void testClassLoader() throws ClassNotFoundException {

//      1.  获取系统的类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
//      2. 获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
//      3. 尝试获取引导类加载器，是获取不到的
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);

//      获取加载当前类的类加载器
        ClassLoader currentClassLoader = Class.forName("reflection.TestReflection").getClassLoader();
        System.out.println("currentClassLoader = " + currentClassLoader);

        //通过系统类加载器来获取src目录下的资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        System.out.println(in);
    }

    /**
     * 测试获取Class方法
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */

    @Test
    public void testClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        获取class对象的方法有三种
//        方法1：
        Class class1 = Person.class;

        Person person = new Person();
//       方法2：
        Class class2 = person.getClass();
//      方法3：
        Class class3 = Class.forName("reflection.Person");

        System.out.println("class1 = " + class1);
        System.out.println("class2 = " + class2);
        System.out.println("class3 = " + class3);

        System.out.println("class1 == class2 : " + class1.equals(class2));
//      通过反射创建一个对象
        Object o = class1.newInstance();
        System.out.println(o);
    }
}
