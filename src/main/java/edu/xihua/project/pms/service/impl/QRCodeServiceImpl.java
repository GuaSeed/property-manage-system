package edu.xihua.project.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.xihua.project.pms.mapper.QRCodeMapper;
import edu.xihua.project.pms.model.dataobject.QRCode;
import edu.xihua.project.pms.service.QRCodeService;
import org.springframework.stereotype.Service;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 10:40 上午
 * @since 1.0
 */
@Service
public class QRCodeServiceImpl
        extends ServiceImpl<QRCodeMapper, QRCode>
        implements QRCodeService {
}
