package edu.xihua.project.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xihua.project.pms.model.dataobject.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/4/4 11:42
 * @since 1.0
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("select a.id as a_id, a.role_name as a_role_name, c.id as c_id, c.permission_name as c_permission_name\n" +
            "from t_role a,\n" +
            "     t_user_role b,\n" +
            "     t_role_permission c\n" +
            "where b.fk_user_id = #{uid}\n" +
            "  and b.fk_role_id = a.id\n" +
            "  and c.fk_role_id = a.id\n" +
            "  and a.is_deleted = false\n" +
            "  and b.is_deleted = false\n" +
            "  and c.is_deleted = false")
    List<Map<String, Object>> getRoleByUserId(int uid);
}