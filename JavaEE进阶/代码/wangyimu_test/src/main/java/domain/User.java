package domain;

/**
 * @author wangyimu
 * @create 2021-12-31 14:46
 */
public class User {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }



    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
