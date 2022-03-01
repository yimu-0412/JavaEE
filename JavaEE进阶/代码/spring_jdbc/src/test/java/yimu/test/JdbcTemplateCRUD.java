package yimu.test;
import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yimu.domain.Account;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-02-27-23:22
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:appc.xml")
public class JdbcTemplateCRUD {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    // 测试修改对象
    public void testUpdate(){
        jdbcTemplate.update("update account set money =? where name =?", 10000, "tom");
    }

    @Test
    // 测试修改对象
    public void testDelete(){
        jdbcTemplate.update("delete from account where name =?","tom");
    }

    @Test
    // 查询操作(全部)
    public void testQueryAll(){
        List<Account> accounts = jdbcTemplate.query("select * from account",
                new BeanPropertyRowMapper<Account>(Account.class));
        for (Account account : accounts) {
            System.out.println(account.getName());
        }
        // System.out.println(accounts);
    }

    @Test
    // 查询操作(某个对象)
    public void testQueryOne(){
        Account forObject = jdbcTemplate.queryForObject("select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class), "lisi");
        System.out.println(forObject);
    }

    @Test
    // 查询操作(对象个数)
    public void testQueryCount(){
        Integer forObject = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
        System.out.println(forObject);
    }
}
