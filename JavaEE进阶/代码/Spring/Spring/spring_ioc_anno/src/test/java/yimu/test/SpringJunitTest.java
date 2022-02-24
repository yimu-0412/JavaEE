package yimu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yimu.config.SpringConfiguration;
import yimu.service.UserService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wangyimu
 * @Program Spring
 * @create 2022-02-19-23:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("classpath:appc.xml")
@ContextConfiguration(classes={SpringConfiguration.class})
public class SpringJunitTest {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test1() throws SQLException {
        System.out.println(dataSource.getConnection());
        userService.save();
    }
}
