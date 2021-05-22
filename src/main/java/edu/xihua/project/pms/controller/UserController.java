package edu.xihua.project.pms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.xihua.project.pms.model.dataobject.User;
import edu.xihua.project.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/22 3:02 下午
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(userService.getOne(new QueryWrapper<User>()
                .orderByDesc(User.COL_ID)
                .last("limit 0,1")
        ));
    }
}
