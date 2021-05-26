package edu.xihua.project.pms.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 10:49 上午
 * @since 1.0
 */
@Data
@TableName(value = "t_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 5636151504626042622L;
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    private Date created;

    /**
     * 更新时间
     */
    @TableField(value = "modified")
    private Date modified;

    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    private String permissionName;

    @TableField(value = "fk_role_id")
    private Integer roleId;
}
