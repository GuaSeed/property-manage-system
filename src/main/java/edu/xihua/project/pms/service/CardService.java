package edu.xihua.project.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dto.CardListDTO;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:24 下午
 * @since 1.0
 */
public interface CardService extends IService<Card> {
    boolean removeCardById(Integer id);

    List<CardListDTO> getListByAvatar();
}
