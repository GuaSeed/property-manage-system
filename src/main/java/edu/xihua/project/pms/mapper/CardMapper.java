package edu.xihua.project.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xihua.project.pms.model.dataobject.Card;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 12:24 下午
 * @since 1.0
 */
@Mapper
public interface CardMapper extends BaseMapper<Card> {
    @Delete("delete from t_card where id = #{id}")
    int deleteCardById(int id);
}
