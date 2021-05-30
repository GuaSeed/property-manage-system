package edu.xihua.project.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xihua.project.pms.mapper.CardMapper;
import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dto.CardListDTO;
import edu.xihua.project.pms.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:25 下午
 * @since 1.0
 */
@Service
public class CardServiceImpl
        extends ServiceImpl<CardMapper, Card>
        implements CardService {
    @Override
    public boolean removeCardById(Integer id) {
        if (id == null) {
            return false;
        }
        return baseMapper.deleteCardById(id) != 0;
    }

    @Override
    public List<CardListDTO> getListByAvatar() {
        return baseMapper.listByAvatar();
    }
}
