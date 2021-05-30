package edu.xihua.project.pms.model.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:38 上午
 * @since 1.0
 */
@Data
@TableName(value = "t_qrcode")
public class QRCode implements Serializable {

    private static final long serialVersionUID = -5989996646560455569L;
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

    @TableField(value = "fk_user_id")
    private Integer userId;

    @TableField(value = "fk_card_id")
    private Integer cardId;

    private String qrcode;

    private Integer express;

    public static final String COL_QRCODE = "qrcode";
}
