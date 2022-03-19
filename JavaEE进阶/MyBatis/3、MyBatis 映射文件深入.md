## 一、动态 sql 语句
1. 动态 sql 语句概述

    &emsp;&emsp;Mybatis的映射文件中，有些时候业务逻辑复杂时，我们的SQL是动态变化的，此时前面的学习就不能满足我们的要求了。

2. 动态 SQL 之<if>

    &emsp;&emsp;根据实体类的不同取值，使用不同的 SQL 语句来进行查询。比如在 id 如果不为空的情况下可以根据 id 查询，如果 username 不为空时还要加入用户名作为条件。这种情况在我们的多条件组合查询中经常会碰到。

    ```
    <select id="findByCondition" parameterType="user" resultType="user">
        select * from user
        <where>
            <if test="id != 0">
                and id = #{id}
            </if>
            <if test="username != null">
                and username = #{username}
            </if>
            <if test="password">
                and password = #{password}
            </if>
        </where>
    </select>
    ```
    当查询条件 id 和 username 都存在时，控制打印的 sql 语句入选

    ```
    //获得MyBatis框架生成的UserMapper接口的实现类
    UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
    User condition = new User();
    condition.setId(1);
    condition.setUsername("zhangsan");
    User user = userMapper.findByCondition(condition);
    ```

    ![SQL语句之if-1](https://raw.githubusercontent.com/yimu-0412/image/master/image/SQL%E8%AF%AD%E5%8F%A5%E4%B9%8Bif-1.png)

    当查询条件只有id存在时，控制台打印的sql语句如下：

    ```
    //获得MyBatis框架生成的UserMapper接口的实现类
    UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
    User condition = new User();
    condition.setId(1);
    User user = userMapper.findByCondition(condition);
    ```

    ![SQL语句之if-2](https://raw.githubusercontent.com/yimu-0412/image/master/image/SQL%E8%AF%AD%E5%8F%A5%E4%B9%8Bif-2.png)
    
3. 动态 SQL 之<foreach>

   循环执行sql的拼接操作，例如：``select * from user where id in (1,2,4)``。 

   ```
   <select id="findByIds" parameterType="list" resultType="user">
        select * from user
        <where>
            <foreach collection="list" open="id in(" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </where>
    </select>
   ```
   测试代码片段如下：

   ```
    // 模拟 ids 的数据
    List<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    ids.add(2);
    List<User> users = mapper.findByIds(ids);
    System.out.println(users);
   ```

   ![SQL语句之foreach](https://raw.githubusercontent.com/yimu-0412/image/master/image/SQL%E8%AF%AD%E5%8F%A5%E4%B9%8Bforeach.png)

   foreach标签的属性含义如下：

    <foreach> 标签用于遍历集合，它的元素：
      
      * collection：代表要遍历的集合元素，注意编写时不要写 #{}。
      * open：代表语句的开始部分。
      * close：代表结束部分。
      * item：代表遍历集合的每个元素，生成的变量名。
      * sperator：代表分隔符。

## 二、SQL 片段抽取、

&emsp;&emsp;Sql中可将重复的sql提取出来，使用时用 include 引用即可，最终达到sql重用的目的

```
     <sql id ="selectUser"> select * from user</sql>

     <select id="findByCondition" parameterType="user" resultType="user">
        <include refid="selectUser"></include>
        <where>
            <if test="id != 0">
                and id = #{id}
            </if>
            <if test="username != null">
                and username = #{username}
            </if>
            <if test="password">
                and password = #{password}
            </if>
        </where>
    </select>

    <select id="findByIds" parameterType="list" resultType="user">
        <include refid="selectUser"></include>
        <where>
            <foreach collection="list" open="id in(" close=")" item="id" separator=",">
                #{id}
            </foreach>
        </where>
    </select>
```
## 三、知识小结

&emsp;&emsp;MyBatis映射文件配置：

*  ``<select>``：查询
*  ``<insert>``：插入
*  ``<update>``：修改
*  ``<delete>``：删除
*  ``<where>``：where 条件
*  ``<if>``：if 判断
*  ``<foreach>``：循环
*  ``<sql>``：sql 片段抽取