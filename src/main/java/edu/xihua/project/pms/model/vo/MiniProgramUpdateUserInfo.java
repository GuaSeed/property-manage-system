package edu.xihua.project.pms.model.vo;

import edu.xihua.project.pms.model.dataobject.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/26 1:02 上午
 * @since 1.0
 */
@Data
public class MiniProgramUpdateUserInfo {
    private String openid;
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String nickName;
    private String province;

    public static User to(MiniProgramUpdateUserInfo updateUserInfo) {
        User user = new User();
        BeanUtils.copyProperties(updateUserInfo, user);
        return user;
    }
}
