package edu.xihua.project.pms.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 10:48 上午
 * @since 1.0
 */
@Data
public class RoleDTO {
    private List<RolePermissionDTO> rolePermissionList;
    private Integer id;
    private String roleName;
}
