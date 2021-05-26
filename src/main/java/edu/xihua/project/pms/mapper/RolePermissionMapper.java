package edu.xihua.project.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xihua.project.pms.model.dataobject.RolePermission;
import edu.xihua.project.pms.model.dataobject.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/4/4 11:42
 * @since 1.0
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
}