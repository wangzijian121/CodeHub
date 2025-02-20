package 虚拟机类加载机制;

public class GetClassLoader {
    public static void main(String[] args) {

        //Launcher$AppClassLoader
        System.out.println(class文件.Demo.class.getClassLoader());
        // Launcher$ExtClassLoader(Extension)
        System.out.println(class文件.Demo.class.getClassLoader().getParent());
        //BootStrap Class Loader 默认是null C++ 实现
        System.out.println(String.class.getClassLoader());
        System.out.println(Long.class.getClassLoader());
        System.out.println(class文件.Demo.class.getClassLoader().getParent());
        System.out.println(class文件.Demo.class.getClassLoader().getClass().getClassLoader());
    }
}
