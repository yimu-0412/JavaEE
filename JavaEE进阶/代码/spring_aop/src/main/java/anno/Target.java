package anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Program
 * @create 2022-03-11-9:03
 */
@Component("target")
public class Target implements TargetInterface {



    @Override
    public void save() {
        System.out.println("save running ···");
        // int i = 1/0;
    }
}
