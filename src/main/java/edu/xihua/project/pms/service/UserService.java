package edu.xihua.project.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.xihua.project.pms.model.dataobject.User;
import edu.xihua.project.pms.model.dto.UserDTO;
import edu.xihua.project.pms.model.vo.Js2CodeSessionVO;
import edu.xihua.project.pms.model.vo.MiniProgramUpdateUserInfo;

/**
 * 用户操作相关接口
 *
 * @author intent zzy.main@gmail.com
 * @date 2021/1/29 15:20
 * @since 1.0
 */
public interface UserService extends IService<User> {
    boolean updateByOpenid(MiniProgramUpdateUserInfo updateUserInfo);

    UserDTO getUserByOpenid(Js2CodeSessionVO js2CodeSessionVO);

    UserDTO getUserByOpenid(String openid);
}

