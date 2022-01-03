package dataTest.test;

import dataTest.config.SpringConfiguration;
import dataTest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-22:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value={"classpath:appl.xml"})
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {

    @Autowired
    private UserService service;

    @Test
    public void test1(){
        service.save();
    }
}
