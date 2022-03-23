package mapper;

import domain.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-23-12:13
 */
public interface OrderMapper {

    @Select("select * ,o.uid oid,u.id ud from orders o,user u where o.uid = u.id")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime",property = "ordertime"),
            @Result(column = "total",property = "total"),
            @Result(column = "ud",property = "user.id"),
            @Result(column = "username",property = "user.username"),
            @Result(column = "password",property = "user.password"),
            @Result(column = "birthday",property = "user.birthday")
    })
    public List<Order> findAll();

    @Select("select * from orders where uid = #{uid}")
    public List<Order> findBByUid(int uid);
}
