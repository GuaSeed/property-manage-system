package edu.xihua.project.pms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.xihua.project.pms.model.dataobject.QRCode;
import edu.xihua.project.pms.model.dto.UserDTO;
import edu.xihua.project.pms.model.vo.CheckQRCodeVO;
import edu.xihua.project.pms.model.vo.GenQRCodeVO;
import edu.xihua.project.pms.service.QRCodeService;
import edu.xihua.project.pms.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 10:32 上午
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/qrcode")
public class QRCodeController {
    @Autowired
    private UserService userService;

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping("/createQRCode")
    public ResponseEntity<?> createQRCode(@RequestBody GenQRCodeVO genQRCodeVO) {
        if (StringUtils.isBlank(genQRCodeVO.getOpenid())) {
            return ResponseEntity.badRequest().body("参数错误");
        }
        UserDTO user = userService.getUserByOpenid(genQRCodeVO.getOpenid());
        if (user == null) {
            return ResponseEntity.badRequest().body("没有该用户");
        }
        if (user.getCard() != null) {
            QRCode qrCode = new QRCode();
            qrCode.setUserId(user.getId());
            qrCode.setCardId(user.getCard().getId());
            qrCode.setQrcode(RandomStringUtils.random(64, 'a', 'z', true, true));
            if (qrCodeService.save(qrCode)) {
                return ResponseEntity.ok(qrCode);
            }
            return ResponseEntity.badRequest().body("创建二维码错误");
        }
        return ResponseEntity.badRequest().body("该用户还未开卡");
    }

    @PostMapping("/checkQRCode")
    public ResponseEntity<String> checkQRCode(@RequestBody CheckQRCodeVO checkQRCodeVO) {
        if (StringUtils.isBlank(checkQRCodeVO.getQrcode())) {
            return ResponseEntity.badRequest().body("参数错误");
        }
        QRCode qrCode = qrCodeService.getOne(new QueryWrapper<QRCode>()
                .eq(QRCode.COL_QRCODE, checkQRCodeVO.getQrcode())
        );
        if (qrCode != null) {
            // 计算过期时间
            if (qrCode.getCreated().getTime() + qrCode.getExpress() * 1000 <= System.currentTimeMillis()) {
                return ResponseEntity.badRequest().body("二维码已过期");
            }
            return ResponseEntity.ok("二维码通过验证");
        }
        return ResponseEntity.badRequest().body("没有该二维码");
    }
}
