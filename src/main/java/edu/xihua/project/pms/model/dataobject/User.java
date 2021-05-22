package edu.xihua.project.pms.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/4/4 11:42
 * @since 1.0
 */
@Data
@TableName(value = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 3497628374598675931L;
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

    public static final String COL_ID = "id";

    public static final String COL_CREATED = "created";

    public static final String COL_MODIFIED = "modified";

    public static final String COL_IS_DELETED = "is_deleted";
}