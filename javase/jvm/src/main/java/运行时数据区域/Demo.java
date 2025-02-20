package 运行时数据区域;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        Person p=new Person();
        new Person();
        p.name = "wangzijian";
        System.out.println(p.name);
        setPerson(p);
        System.out.println(p.name);

        String name = "wangzijian";
        setStr(name);
        System.out.println(name);


        char c=123;
        System.out.println();



    }
    public static void setStr(String name) {
        name = "wangzijian_new";

    }

    public static void setPerson(Person p) {
        p.name = "wangzijian_new";
    }
}

class Person {
    String name="initName";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}