package edu.xihua.project.pms.model.dto;

import lombok.Data;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 1:32 下午
 * @since 1.0
 */
@Data
public class CardListDTO {
    private Integer id;
    private Integer userId;
    private String description;
    private String avatarUrl;
}
