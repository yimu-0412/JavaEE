package test;

import anno.TargetInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author
 * @Program
 * @create 2022-03-11-21:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:appl-anno.xml")
public class AnnoTest {

    @Autowired
    private TargetInterface target;

    @Test
    public void test(){
        target.save();
    }
}
