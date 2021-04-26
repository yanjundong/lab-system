package com.coder.labsystem.core.chuchai.service;

import com.coder.labsystem.core.chuchai.repository.ChuChaiRepository;
import com.coder.labsystem.core.chuchai.repository.CustomChuChaiRepository;
import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.model.bo.ChuChaiBO;
import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.model.entity.UserBasicInfo;
import com.coder.labsystem.model.query.ChuChaiQuery;
import com.coder.labsystem.model.vo.ChuChaiVo;
import com.coder.labsystem.model.vo.PageResp;
import com.coder.labsystem.security.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author : JQK
 * @date : 2021-04-19 23:09
 * @description :
 */
@Service
public class ChuChaiService {

    private final ChuChaiRepository chuChaiRepository;
    private final CustomChuChaiRepository customChuChaiRepository;

    public ChuChaiService(ChuChaiRepository chuChaiRepository, CustomChuChaiRepository customChuChaiRepository) {
        this.chuChaiRepository = chuChaiRepository;
        this.customChuChaiRepository = customChuChaiRepository;
    }

    /**
     * 添加出差记录
     * @param chuChai
     * @return
     */
    public boolean addChuChai(ChuChaiBO chuChai) {
        UserBasicInfo currentUser = SecurityUtil.getCurrentUser();
        ChuChai chuChaiDO = new ChuChai(currentUser.getUsername(), chuChai);
        ChuChai insert = chuChaiRepository.insert(chuChaiDO);
        if (insert == null) {
            throw new DaoException("提交出差记录错误");
        }
        return true;
    }

    /**
     * 获取个人的出差记录
     * @return
     */
    public PageResp<ChuChaiVo> getChuChais(ChuChaiQuery query) {
        UserBasicInfo currentUser = SecurityUtil.getCurrentUser();
        query.adjust();
        Page<ChuChaiVo> chuChais = customChuChaiRepository.getByUsernameAndBaoXiaoQuery(currentUser.getUsername(), query);
        PageResp<ChuChaiVo> pageResp = new PageResp<>(chuChais);
        return pageResp;
    }

    /**
     * 修改出差记录
     *
     * @param chuChai
     * @return
     */
    public boolean updateChuChai(String id, ChuChaiBO chuChai) {
        UserBasicInfo currentUser = SecurityUtil.getCurrentUser();
        ChuChai chuChaiDO = new ChuChai(id, currentUser.getUsername(), chuChai);
        boolean update = customChuChaiRepository.updateChuChai(chuChaiDO);
        if (!update) {
            throw new DaoException("出差记录修改错误");
        }
        return true;
    }

    /**
     * 删除出差记录
     * @param id 出差id
     * @return
     */
    public boolean removeChuChai(String id) {
        chuChaiRepository.deleteById(id);
        boolean exist = chuChaiRepository.existsById(id);
        if (exist) {
            throw new DaoException("出差记录删除错误");
        }
        return true;
    }

    /**
     * 批量删除出差记录
     * @param ids 出差id
     * @return
     */
    public boolean removeChuChais(String ids) {
        String[] split = ids.split("#");
        for (String id : split) {
            chuChaiRepository.deleteById(id);
        }
        return true;
    }
}
