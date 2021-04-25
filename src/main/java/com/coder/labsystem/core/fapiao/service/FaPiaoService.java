package com.coder.labsystem.core.fapiao.service;

import com.coder.labsystem.core.fapiao.repository.FaPiaoRepository;
import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.model.bo.FaPiaoBo;
import com.coder.labsystem.model.entity.FaPiao;
import com.coder.labsystem.model.entity.UserBasicInfo;
import com.coder.labsystem.model.vo.PageResp;
import com.coder.labsystem.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author : JQK
 * @date : 2021-04-21 10:44
 * @description :
 */
@Service
public class FaPiaoService {
    private final static Logger LOG = LoggerFactory.getLogger(FaPiaoService.class);

    private final FaPiaoRepository faPiaoRepository;

    public FaPiaoService(FaPiaoRepository faPiaoRepository) {
        this.faPiaoRepository = faPiaoRepository;
    }

    /**
     * 上传发票相关信息
     * @param faPiaoBo
     * @return
     */
    public boolean addFaPiao(FaPiaoBo faPiaoBo) {
        UserBasicInfo currentUser = SecurityUtil.getCurrentUser();
        FaPiao faPiao = new FaPiao(currentUser.getUsername(), faPiaoBo);
        FaPiao insert = faPiaoRepository.insert(faPiao);
        if (insert == null) {
            throw new DaoException("发票相关信息添加错误");
        }
        LOG.info("用户：{} 在 {} 添加发票信息，金额为 {}", currentUser.getUsername(), faPiao.getCreateDateTime(), faPiao.getMoney());
        return true;
    }


    public PageResp listFaPiao() {

        return null;
    }
}
