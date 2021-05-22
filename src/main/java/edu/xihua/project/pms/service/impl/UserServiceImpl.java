package edu.xihua.project.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xihua.project.pms.mapper.UserMapper;
import edu.xihua.project.pms.model.dataobject.User;
import edu.xihua.project.pms.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author intent zzy.main@gmail.com
 * @date 2021/1/29 21:01
 * @since 1.0
 */
@Log4j2
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
}

