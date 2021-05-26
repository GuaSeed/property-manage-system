package edu.xihua.project.pms.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 12:38 上午
 * @since 1.0
 */
@Data
@TableName(value = "t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1421598250319651705L;
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

    private String roleName;
}
