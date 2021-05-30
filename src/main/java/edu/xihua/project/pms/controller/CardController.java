package edu.xihua.project.pms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dataobject.User;
import edu.xihua.project.pms.model.dto.CardDTO;
import edu.xihua.project.pms.model.dto.UserDTO;
import edu.xihua.project.pms.model.vo.AddCardVO;
import edu.xihua.project.pms.model.vo.CardListVO;
import edu.xihua.project.pms.model.vo.DeleteCardVO;
import edu.xihua.project.pms.service.CardService;
import edu.xihua.project.pms.service.UserService;
import edu.xihua.project.pms.util.RoleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:26 下午
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestBody DeleteCardVO deleteCardVO) {
        if (StringUtils.isBlank(deleteCardVO.getOpenid()) || deleteCardVO.getCid() == null) {
            return ResponseEntity.badRequest().body("参数错误");
        }
        UserDTO user = userService.getUserByOpenid(deleteCardVO.getOpenid());
        if (user == null) {
            return ResponseEntity.badRequest().body("没有该用户");
        }
        // 检查是不是管理员
        if (!RoleUtils.isAdmin(user)) {
            return ResponseEntity.badRequest().body("权限错误");
        }
        // 检查有没有该卡片
        Card card = cardService.getById(deleteCardVO.getCid());
        if (card == null) {
            return ResponseEntity.badRequest().body("没有该卡片");
        }
        if (cardService.removeCardById(card.getId())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body("参数错误");
    }

    @PostMapping(value = "/list")
    public ResponseEntity<?> list(@RequestBody CardListVO cardListVO) {
        if (StringUtils.isBlank(cardListVO.getOpenid())) {
            return ResponseEntity.badRequest().body("参数错误");
        }
        UserDTO user = userService.getUserByOpenid(cardListVO.getOpenid());
        if (user == null) {
            return ResponseEntity.badRequest().body("没有该用户");
        }
        // 检查是不是管理员
        if (!RoleUtils.isAdmin(user)) {
            return ResponseEntity.badRequest().body("权限错误");
        }
        // 返回所有卡
        List<Card> list = cardService.list();
        return ResponseEntity.ok(CardDTO.fromList(list));
    }

    @PostMapping(value = "/bind")
    public ResponseEntity<?> addCard(@RequestBody AddCardVO addCardVO) {
        if (StringUtils.isBlank(addCardVO.getOpenid()) || addCardVO.getUid() == null) {
            return ResponseEntity.badRequest().body("参数错误");
        }
        UserDTO user = userService.getUserByOpenid(addCardVO.getOpenid());
        if (user == null) {
            return ResponseEntity.badRequest().body("没有该用户");
        }
        // 检查是不是管理员
        if (!RoleUtils.isAdmin(user)) {
            return ResponseEntity.badRequest().body("权限错误");
        }
        // 检查开卡的用户id是否有用户
        User userCard = userService.getById(addCardVO.getUid());
        if (userCard == null) {
            return ResponseEntity.badRequest().body("没有该用户");
        }
        // 检查该用户是否已经开卡
        Card card = cardService.getOne(new QueryWrapper<Card>()
                .eq(Card.COL_USER_ID, userCard.getId())
        );
        if (card != null) {
            return ResponseEntity.badRequest().body("该用户已绑卡");
        }
        // 开卡
        Card newCard = new Card();
        newCard.setUserId(userCard.getId());
        newCard.setDescription(addCardVO.getDescription());
        try {
            if (cardService.save(newCard)) {
                return ResponseEntity.ok("绑定成功");
            }
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("该id已有卡片");
        }
        return ResponseEntity.badRequest().body("绑定失败");
    }
}
