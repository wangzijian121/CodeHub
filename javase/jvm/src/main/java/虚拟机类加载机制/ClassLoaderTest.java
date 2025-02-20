package 虚拟机类加载机制;

public class ClassLoaderTest {

    public static void main(String[] args) {

        String bootStrapPath = System.getProperty("sun.boot.class.path");
        String extPath = System.getProperty("java.ext.dirs");
        String appPath = System.getProperty("java.class.path");
        System.out.println("---------bootStrapPath-----------");
        System.out.println(bootStrapPath.replaceAll(";",System.lineSeparator()));
        System.out.println("----------extPath----------");
        System.out.println(extPath.replaceAll(";",System.lineSeparator()));
        System.out.println("----------appPath----------");
        System.out.println(appPath.replaceAll(";",System.lineSeparator()));

    }

}
