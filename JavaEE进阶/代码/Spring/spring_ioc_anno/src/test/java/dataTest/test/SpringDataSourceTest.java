package dataTest.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.jolbox.bonecp.BoneCPDataSource;
import dataTest.config.SpringConfiguration;
import dataTest.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-17:07
 */
public class SpringDataSourceTest {
    @Test
    public void testUserServiceAnnoConfig(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService service = (UserService) ac.getBean("service");
        service.save();
        ac.close();
    }

    @Test
    public void testC3p0AnnoConfig() throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        ac.close();
    }

    @Test
    public void testDruidAnnoConfig() throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DruidDataSource druidDataSource = (DruidDataSource) ac.getBean("druidDataSource");
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
        ac.close();
    }

}
