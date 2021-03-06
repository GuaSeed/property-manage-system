package edu.xihua.project.pms.model.dto;

import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dataobject.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 10:37 上午
 * @since 1.0
 */
@Data
public class UserDTO {
    private Integer id;
    private String openid;
    private long createdTimestamp;
    private long modifiedTimestamp;
    //    private String sessionKey;
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String nickName;
    private String province;
    private List<RoleDTO> roleList;
    private CardDTO card;

    public static UserDTO from(User user, Map<Long, RoleDTO> roleDTOMap, Card card) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        if (user.getCreated() != null) {
            userDTO.setCreatedTimestamp(user.getCreated().getTime());
        }
        if (user.getModified() != null) {
            userDTO.setModifiedTimestamp(user.getModified().getTime());
        }
        userDTO.setRoleList(new ArrayList<>(roleDTOMap.values()));
        userDTO.setCard(CardDTO.from(card));
        return userDTO;
    }
}
