package dao;

import javafx.beans.property.SetProperty;
import jdk.nashorn.internal.ir.JumpToInlinedFinally;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-15:24
 */

// 对 Users 表的操作进行封装
// 1. 实现新增用户,用来实现注册功能
// 2. 根据用户名来查找用户对象(这个是登录时的必要操作)
// 3. 根据用户 id 来查找用户对象（这个是后续在博客页面显示作者的时候，必要的方法）
public class UsersDao {
    public void insert(Users user){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL
            String sql = "insert into users values(null,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if(ret == 1){
                System.out.println("插入成功！");
            }else{
                System.out.println("插入失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public Users selectByName(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL
            String sql = "select * from users where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集合
            if(resultSet.next()){
                Users user = new Users();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 关闭释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public Users selectById(int userId){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. 和数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL
            String sql = "select * from users where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集合
            if(resultSet.next()){
                Users user = new Users();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. 关闭释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();

        Users users = new Users();
        users.setUserId(1);
        users.setUsername("张三");
        users.setPassword("1234");

        // 1. 测试插入用户
        usersDao.insert(users);
        // usersDao.insert(users);

        // 2. 测试根据用户名查询用户
        /*Users users1 = usersDao.selectByName("张三");
        System.out.println(users1);
*/
        // 3. 测试根据用户 id 查找用户
        Users users2 = usersDao.selectById(1);
        System.out.println(users2);

    }
}
