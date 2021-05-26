package edu.xihua.project.pms.model.dto;

import edu.xihua.project.pms.model.dataobject.RolePermission;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 10:50 上午
 * @since 1.0
 */
@Data
public class RolePermissionDTO {
    private Integer id;
    private String permissionName;

    public static List<RolePermissionDTO> from(List<RolePermission> rolePermissionList) {
        List<RolePermissionDTO> rolePermissionDTOList = new ArrayList<>(rolePermissionList.size());
        rolePermissionList.forEach(rolePermission -> {
            rolePermissionDTOList.add(RolePermissionDTO.from(rolePermission));
        });
        return rolePermissionDTOList;
    }

    public static RolePermissionDTO from(RolePermission rolePermission) {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        BeanUtils.copyProperties(rolePermission, rolePermissionDTO);
        return rolePermissionDTO;
    }
}
