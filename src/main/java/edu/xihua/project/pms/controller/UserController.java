package edu.xihua.project.pms.controller;

import com.google.gson.Gson;
import edu.xihua.project.pms.constant.MiniProgramConst;
import edu.xihua.project.pms.model.dto.UserDTO;
import edu.xihua.project.pms.model.vo.Js2CodeSessionVO;
import edu.xihua.project.pms.model.vo.MiniProgramUpdateUserInfo;
import edu.xihua.project.pms.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/22 3:02 下午
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${config.miniprogram.app-id}")
    private String appMiniProgramId;

    @Value("${config.miniprogram.app-secret}")
    private String appMiniProgramSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/miniprogram/login/{code}")
    public ResponseEntity<?> miniProgramLoginIn(@PathVariable("code") String code) {
        // 先请求微信服务器
        String url = String.format(MiniProgramConst.JS_CODE2_SESSION_URL, appMiniProgramId, appMiniProgramSecret, code);
        String response = restTemplate.getForObject(url, String.class);
        Js2CodeSessionVO js2CodeSessionVO = new Gson().fromJson(response, Js2CodeSessionVO.class);
        if (StringUtils.isBlank(js2CodeSessionVO.getOpenid())) {
            return ResponseEntity.notFound().build();
        }
        // 这里就获取到openid了
        UserDTO user = userService.getUserByOpenid(js2CodeSessionVO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/miniprogram/updateUserInfo")
    public ResponseEntity<Boolean> miniProgramUpdateUserInfo(@RequestBody MiniProgramUpdateUserInfo updateUserInfo) {
        if (StringUtils.isBlank(updateUserInfo.getOpenid())) {
            ResponseEntity.badRequest().body(false);
        }
        boolean b = userService.updateByOpenid(updateUserInfo);
        return b ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }
}
