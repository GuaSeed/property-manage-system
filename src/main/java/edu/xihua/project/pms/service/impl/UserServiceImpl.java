package edu.xihua.project.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xihua.project.pms.constant.RoleConst;
import edu.xihua.project.pms.mapper.UserMapper;
import edu.xihua.project.pms.mapper.UserRoleMapper;
import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dataobject.User;
import edu.xihua.project.pms.model.dataobject.UserRole;
import edu.xihua.project.pms.model.dto.RoleDTO;
import edu.xihua.project.pms.model.dto.RolePermissionDTO;
import edu.xihua.project.pms.model.dto.UserDTO;
import edu.xihua.project.pms.model.vo.Js2CodeSessionVO;
import edu.xihua.project.pms.model.vo.MiniProgramUpdateUserInfo;
import edu.xihua.project.pms.service.CardService;
import edu.xihua.project.pms.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author intent zzy.main@gmail.com
 * @date 2021/1/29 21:01
 * @since 1.0
 */
@Log4j2
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private CardService cardService;

    @Override
    public boolean updateByOpenid(MiniProgramUpdateUserInfo updateUserInfo) {
        return this.update(MiniProgramUpdateUserInfo.to(updateUserInfo), new QueryWrapper<User>()
                .eq(User.COL_OPENID, updateUserInfo.getOpenid()
                ));
    }

    @Override
    public UserDTO getUserByOpenid(Js2CodeSessionVO js2CodeSessionVO) {
        // 首先获取到用户
        User user = this.getOne(new QueryWrapper<User>()
                .eq(User.COL_OPENID, js2CodeSessionVO.getOpenid())
        );
        if (user == null) {
            // 保存到数据库
            user = new User();
            user.setOpenid(js2CodeSessionVO.getOpenid());
            user.setSessionKey(js2CodeSessionVO.getSessionKey());
            if (this.save(user)) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(RoleConst.DEFAULT_ROLE_ID);
                userRoleMapper.insert(userRole);
            }
        } else {
            if (StringUtils.isNoneBlank(js2CodeSessionVO.getSessionKey())) {
                user.setSessionKey(js2CodeSessionVO.getSessionKey());
                this.updateById(user);
            }
        }
        // 根据用户id获取角色
        List<Map<String, Object>> roleByUserId = userRoleMapper.getRoleByUserId(user.getId());
        Map<Long, RoleDTO> roleDTOMap = new HashMap<>();
        for (Map<String, Object> map : roleByUserId) {
            Long roleId = (Long) map.get("a_id");
            if (roleId == null) {
                continue;
            }
            RoleDTO roleDTO = roleDTOMap.get(roleId);
            List<RolePermissionDTO> rolePermissionDTOList;
            RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
            rolePermissionDTO.setId(((Long) map.get("c_id")).intValue());
            rolePermissionDTO.setPermissionName((String) map.get("c_permission_name"));
            if (roleDTO == null) {
                roleDTO = new RoleDTO();
                roleDTO.setId(roleId.intValue());
                roleDTO.setRoleName((String) map.get("a_role_name"));
                rolePermissionDTOList = new ArrayList<>(roleByUserId.size());
                roleDTO.setRolePermissionList(rolePermissionDTOList);
                roleDTOMap.put(roleId, roleDTO);
            } else {
                rolePermissionDTOList = roleDTO.getRolePermissionList();
            }
            rolePermissionDTOList.add(rolePermissionDTO);
        }
        Card card = cardService.getOne(new QueryWrapper<Card>()
                .eq(Card.COL_USER_ID, user.getId())
        );
        return UserDTO.from(user, roleDTOMap, card);
    }

    @Override
    public UserDTO getUserByOpenid(String openid) {
        Js2CodeSessionVO js2CodeSessionVO = new Js2CodeSessionVO();
        js2CodeSessionVO.setOpenid(openid);
        return getUserByOpenid(js2CodeSessionVO);
    }


}

