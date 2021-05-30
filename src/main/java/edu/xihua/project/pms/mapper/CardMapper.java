package edu.xihua.project.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xihua.project.pms.model.dataobject.Card;
import edu.xihua.project.pms.model.dto.CardListDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:24 下午
 * @since 1.0
 */
@Mapper
public interface CardMapper extends BaseMapper<Card> {
    @Delete("delete from t_card where id = #{id}")
    int deleteCardById(int id);

    @Select("SELECT a.id, fk_user_id AS userId, description, b.avatar_url\n" +
            "FROM t_card a,\n" +
            "     t_user b\n" +
            "WHERE a.is_deleted = 0\n" +
            "  AND b.is_deleted = 0\n" +
            "  AND a.fk_user_id = b.id")
    List<CardListDTO> listByAvatar();
}
