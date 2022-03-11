package aop;

/**
 * @author
 * @Program
 * @create 2022-03-11-9:03
 */
public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("save running ···");
        // int i = 1/0;

    }
}
