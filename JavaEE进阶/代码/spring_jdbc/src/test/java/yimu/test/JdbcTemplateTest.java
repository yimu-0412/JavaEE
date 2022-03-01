package yimu.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author
 * @Program
 * @create 2022-02-27-21:56
 */
public class JdbcTemplateTest {

    @Test
    public void test2(){
        // 1.创建数据源
        ApplicationContext app = new ClassPathXmlApplicationContext("appc.xml");
        // 2.创建 JdbcTemplate 对象
        // 3.设置数据源给 JdbcTenplate 对象
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");
        // 4.执行操作：添加操作
        int row = jdbcTemplate.update("insert into account values(?,?)", "lisi", 3000);
        System.out.println(row);
    }

    @Test
    public void test1() throws PropertyVetoException {
        // 1.创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbctest");
        dataSource.setUser("root");
        dataSource.setPassword("0412");
        // 2.创建 JdbcTemplate 对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 3.设置数据源给 JdbcTenplate 对象
        jdbcTemplate.setDataSource(dataSource);
        // 4.执行操作
        int row = jdbcTemplate.update("insert into account values(?,?)", "tom", 2000);
        System.out.println(row);

    }
}
