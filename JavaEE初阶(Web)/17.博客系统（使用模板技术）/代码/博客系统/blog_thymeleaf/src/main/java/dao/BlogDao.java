package dao;

import com.sun.crypto.provider.BlowfishKeyGenerator;
import sun.applet.Main;
import sun.security.pkcs11.Secmod;

import java.lang.annotation.ElementType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangyimu
 * @Program 2022
 * @create 2022-02-13-12:23
 */
// 封装对于 blog 表的操作
// 1. 新增博客
// 2. 获取博客列表
// 3. 根据博客 id 获取到指定博客
// 4. 根据博客 id 删除博客
public class BlogDao {
    public void insert(Blog blog){
        System.out.println("新增博客");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // 1. 建立连接
            connection = DBUtil.getConnection();
            // 2. 构造 SQL 语句
            String sql = "insert into blog values(null,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,blog.getTitle());
            statement.setString(2,blog.getContent());
            statement.setTimestamp(3,blog.getPostTime());
            statement.setInt(4,blog.getUserId());
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if(ret == 1){
                System.out.println("插入博客成功！");
            }else{
                System.out.println("插入博客失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 4. 释放资源
            DBUtil.close(connection,statement,null);
        }

    }

    public List<Blog> selectAll() {
        List<Blog> blogs = new ArrayList<Blog>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            // 1. 建立连接
            connection = DBUtil.getConnection();
            // 2. 构造 SQL
            String sql = "select * from blog order by postTime desc";
            statement = connection.prepareStatement(sql);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历集合结果
            while(resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                String content = resultSet.getString("content");
                if(content.length() > 90){
                    content = content.substring(0,90) + "···";
                }
                blog.setContent(content);
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                blogs.add(blog);

            }
            return blogs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 5. 释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public Blog selectOne(int blogId){
        System.out.println("查找指定博客");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. 建立连接
            connection = DBUtil.getConnection();
            // 2. 构造 SQL
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历集合结果,由于此处是基于 自增主键 查询的，查询结果要么是1条记录，要么是0条
            if(resultSet.next()){
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                blog.setUserId(resultSet.getInt("userId"));
                return blog;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 5. 释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }


    public void delete(int blogId){
        System.out.println("删除指定博客!");
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            // 1. 与数据库建立连接
            connection = DBUtil.getConnection();
            // 2. 拼装 SQL
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, blogId);
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if(ret == 1){
                System.out.println("删除博客成功！");
            }else{
                System.out.println("删除博客失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) {
        BlogDao blogDao = new BlogDao();

        Blog blog = new Blog();
        blog.setTitle("第一篇博客");
        blog.setContent("从今天开始,要好好学习,Lorem ipsum dolor sit, amet consectetur adipisicing elit. Excepturi sequi optio hic quisquam dolores aut maiores enim laborum ex, iusto ad labore consectetur ullam, expedita totam eligendi a veniam pariatur.\n" +
                "\n" +
                "从今天开始,要好好学习,Lorem ipsum dolor sit, amet consectetur adipisicing elit. Excepturi sequi optio hic quisquam dolores aut maiores enim laborum ex, iusto ad labore consectetur ullam, expedita totam eligendi a veniam pariatur.\n" +
                "\n" +
                "从今天开始,要好好学习,Lorem ipsum dolor sit, amet consectetur adipisicing elit. Excepturi sequi optio hic quisquam dolores aut maiores enim laborum ex, iusto ad labore consectetur ullam, expedita totam eligendi a veniam pariatur.");
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blog.setUserId(2);

        // 1. 验证插入功能
        // blogDao.insert(blog);

        // 2. 测试查找博客列表
        List<Blog> blogs = blogDao.selectAll();
        System.out.println(blogs);
        // 3. 测试查找指定博客功能
        // Blog blog1 = blogDao.selectOne(3);
        // System.out.println(blog1);
        // 4. 测试删除指定博客功能
        blogDao.delete(11);

    }
}
