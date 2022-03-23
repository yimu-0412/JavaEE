package mapper;

import domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-23-17:51
 */
public interface RoleMapper {

    @Select("select * from sys_user_role ur,sys_role r where ur.roleId = r.id and ur.userId = #{uid}")
    public List<Role> findByUid(int uid);
}
