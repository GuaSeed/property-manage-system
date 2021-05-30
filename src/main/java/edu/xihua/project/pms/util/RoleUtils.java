package edu.xihua.project.pms.util;

import edu.xihua.project.pms.constant.RoleConst;
import edu.xihua.project.pms.model.dto.RoleDTO;
import edu.xihua.project.pms.model.dto.UserDTO;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:32 下午
 * @since 1.0
 */
public class RoleUtils {
    public static boolean isAdmin(UserDTO userDTO) {
        List<RoleDTO> roleList = userDTO.getRoleList();
        for (RoleDTO role : roleList) {
            if (RoleConst.ADMIN_ROLE_ROME.equals(role.getRoleName())) {
                return true;
            }
        }
        return false;
    }
}
