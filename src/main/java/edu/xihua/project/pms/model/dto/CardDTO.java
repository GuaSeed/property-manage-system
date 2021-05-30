package edu.xihua.project.pms.model.dto;

import edu.xihua.project.pms.model.dataobject.Card;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 1:32 下午
 * @since 1.0
 */
@Data
public class CardDTO {
    private long createdTimestamp;
    private long modifiedTimestamp;
    private Integer id;
    private Integer userId;
    private String description;

    public static CardDTO from(Card card) {
        CardDTO cardDTO = new CardDTO();
        if (card == null) {
            return null;
        }
        BeanUtils.copyProperties(card, cardDTO);
        if (card.getCreated() != null) {
            cardDTO.setCreatedTimestamp(card.getCreated().getTime());
        }
        if (card.getModified() != null) {
            cardDTO.setModifiedTimestamp(card.getModified().getTime());
        }
        return cardDTO;
    }

    public static List<CardDTO> fromList(List<Card> cardList) {
        if (cardList.isEmpty()) {
            return Collections.emptyList();
        }
        List<CardDTO> list = new ArrayList<>(cardList.size());
        for (Card card : cardList) {
            list.add(from(card));
        }
        return list;
    }
}
