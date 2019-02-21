package algorithm.foroffer;

/**
 * 单例模式：恶汉式，线程安全
 * 步骤：
 *      1. 创建并初始化 private static 变量
 *      2. 私有化构造方法
 *      3. 创建返回单例对象的 public static getInstance 方法
 */
class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){   }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }

    public static void test(){
        Singleton1 test01 = Singleton1.getInstance();
        Singleton1 test02 = Singleton1.getInstance();
        boolean isEquals = test01.equals(test02);
        String info = String.format("%s equals %s, %b", test01, test02, isEquals);
        System.out.println(info);
    }
}

/**
 * 单例模式：懒汉式，线程不安全
 * 步骤：
 *     1. 创建 private static 变量
 *     2. 私有化构造方法
 *     3. 创建返回单例对象的 public static getInstance 方法，如果单例为 null，则创建该对象，之后返回该单例
 */
class Singleton2{
    private static Singleton2 INSTANCE;

    private Singleton2(){}

    public static Singleton2 getInstance(){
        if (INSTANCE == null) INSTANCE = new Singleton2();
        return INSTANCE;
    }

    public static void test(){
        Singleton2 instance01 = Singleton2.getInstance();
        Singleton2 instance02 = Singleton2.getInstance();
        boolean isEquals = instance01.equals(instance02);
        String info = String.format("%s equals %s, %b", instance01, instance02, isEquals);
        System.out.println(info);
    }
}


/**
 * 单例模式：静态代码块懒汉式，线程安全
 */
class Singleton3 {
    private static Singleton3  INSTANCE;

    static{
        INSTANCE = new Singleton3 ();
    }

    private Singleton3 (){}

    public static Singleton3  getInstance(){
        return INSTANCE;
    }

    public static void test(){
        Singleton3  instance1 = Singleton3 .getInstance();
        Singleton3  instance2 = Singleton3 .getInstance();
        boolean isEquals = instance1.equals(instance2);
        String info = String.format("%s equals %s, %b", instance1, instance2, isEquals);
        System.out.println(info);
    }
}

/**
 * 单例模式：静态内部类，线程安全【推荐，why】
 */
class Singleton4 {
    // todo, what are its advantages
    private final static class SingletonHolder {
        private final static Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4(){}

    public static Singleton4 getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void test(){
        Singleton4 instance1 = Singleton4.getInstance();
        Singleton4 instance2 = Singleton4.getInstance();
        boolean isEquals = instance1.equals(instance2);
        String info = String.format("%s equals %s, %b", instance1, instance2, isEquals);
        System.out.println(info);
    }
}


/**
 * 单例模式：synchronized懒汉式，线程安全，多线程环境下效率不高
 */
class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5 (){}

    public static synchronized Singleton5  getInstance(){
        if (INSTANCE == null) INSTANCE = new Singleton5();
        return INSTANCE;
    }

    public static void test(){
        Singleton5 instance1 = Singleton5.getInstance();
        Singleton5 instance2 = Singleton5.getInstance();
        boolean isEquals = instance1.equals(instance2);
        String info = String.format("%s equals %s, %b", instance1, instance2, isEquals);
        System.out.println(info);
    }
}

/**
 * 单例模式：(双重校验锁)双重空校验 + 中间的synchronized代码块，线程安全【推荐】
 */
class Singleton6{
    // todo, volatile 关键字
    private volatile static Singleton6 INSTANCE;

    private Singleton6(){}

    public static Singleton6 getInstance(){
        if (INSTANCE == null){
            synchronized(Singleton6.class){
                if (INSTANCE == null) INSTANCE = new Singleton6();
            }
        }
        return INSTANCE;
    }

    public static void test(){
        Singleton6 instance1 = Singleton6.getInstance();
        Singleton6 instance2 = Singleton6.getInstance();
        boolean isEquals = instance1.equals(instance2);
        String info = String.format("%s equals %s, %b", instance1, instance2, isEquals);
        System.out.println(info);
    }
}


/**
 * 单例模式：单个实例的枚举，线程安全【推荐】
 */
enum Singleton7{
    //    INSTANCE("singleton instance");
    //    private String name;
    //    Singleton7(String name){this.name = name; }
    INSTANCE;

    public static void test(){
        Singleton7 instance1 = Singleton7.INSTANCE;
        Singleton7 instance2 = Singleton7.INSTANCE;
        boolean isEquals = instance1.equals(instance2);
        String info = String.format("%s equals %s, %b", instance1, instance2, isEquals);
        System.out.println(info);
    }
}

/**
 * Created by liyazhou on 2017/5/22.
 * 面试题2：实现Singleton模式
 */
public class Test02 {
    public static void main(String[] args){
        Singleton1.test();
        Singleton2.test();
        Singleton3.test();
        Singleton4.test();
        Singleton5.test();
        Singleton6.test();
        Singleton7.test();
    }
}
