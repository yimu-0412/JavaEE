package yimuTest.test;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-01-12:13
 */
public class DataSourceTest {

    @Test
    // 测试手动创建 c3p0 数据源
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/");
        dataSource.setUser("root");
        dataSource.setPassword("0412");
        Connection c = dataSource.getConnection();
        System.out.println(c);
    }

    @Test
    // 测试手动创建 druid 数据源
    public void test2() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("0412");
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    // 测试手动创建 c3p0 数据源(加载 propertise 配置文件)
    public void test3() throws Exception {
        // 读取配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 创建数据源对象，设置连接参数
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        // 获取资源
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    // 测试 Spring 容器创建 c3p0 数据源
    public void test4() throws Exception {
        ApplicationContext app = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource =
                (DataSource)app.getBean("dataSource");
        //DataSource dataSource = app.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    // 测试 Spring 容器创建 druid 数据源
    public void test5() throws SQLException {
        ApplicationContext app = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dataSource = (DruidDataSource) app.getBean("druidDataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}















































