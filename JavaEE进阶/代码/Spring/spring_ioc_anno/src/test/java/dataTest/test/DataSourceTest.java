package dataTest.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author wangyimu
 * @Program Spring_5
 * @create 2022-01-03-15:29
 */
public class DataSourceTest {

    @Test
    // 手动创建 c3p0 连接池
    public void testC3p0() throws Exception {
        // 1.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 2.设置数据源连接参数
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/");
        dataSource.setUser("root");
        dataSource.setPassword("0412");
        // 3.获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 手动创建 Druid 连接池
    public void testDruid() throws Exception {
        // 1.创建数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        // 2.设置数据库连接参数
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("0412");
        // 3.获取连接对象
        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testBonecp() throws SQLException {
        // 1.创建数据源
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        // 2.设置数据源连接参数
        boneCPDataSource.setDriverClass("com.mysql.jdbc.Driver");
        boneCPDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/");
        boneCPDataSource.setUsername("root");
        boneCPDataSource.setPassword("0412");
        // 3.获取连接数据
        Connection connection = boneCPDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 读取配置文件创建 C3P0 数据源
    public void testC3p0Propertise() throws Exception {
        // 1.加载类路径下的 jdbc.propertise 文件
        ResourceBundle rb= ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 2.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 3.设置数据源连接参数
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        // 4.获取数据源数据
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 读取配置文件创建 Druid 数据源
    public void testDruitPropertise() throws Exception {
        // 1.加载类路径下的 jdbc.propertise 文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 2.创建数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        // 3.设置数据源连接参数
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        // 4.获取数据源数据
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 读取配置文件创建 BoneCp 数据源
    public void testBonepcPropertise() throws Exception {
        // 1.加载类路径下的 jdbc.propertise 文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        // 2.创建数据源
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        // 3.设置数据源连接参数
        boneCPDataSource.setDriverClass(driver);
        boneCPDataSource.setJdbcUrl(url);
        boneCPDataSource.setUsername(username);
        boneCPDataSource.setPassword(password);
        // 4.获取数据源数据
        Connection connection = boneCPDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 从 Spring 容器中获取 C3P0 数据源
    public void testC3p0Spring() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        DataSource dataSource = (DataSource)app.getBean("dataSource");
        /*DataSource data = app.getBean(DataSource.class);*/
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    // 从 Spring 容器中获取 Druid 数据源
    public void testDruidSpring() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        /*DataSource druid = (DataSource) app.getBean("druid");*/
        DruidDataSource druid = app.getBean(DruidDataSource.class);
        Connection connection = druid.getConnection();
        System.out.println(connection);
    }

    @Test
    // 从 Spring 容器中获取 Druid 数据源
    public void testBonecpSpring() throws Exception {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("appl.xml");
        /*BoneCPDataSource boneCP = (BoneCPDataSource) app.getBean("boneCP");*/
        BoneCPDataSource boneCP= app.getBean(BoneCPDataSource.class);
        Connection connection = boneCP.getConnection();
        System.out.println(connection);
    }
}
