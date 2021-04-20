package com.coder.labsystem.core.chuchai.controller;

import com.coder.labsystem.core.chuchai.service.ChuChaiService;
import com.coder.labsystem.model.bo.ChuChaiBo;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import org.springframework.web.bind.annotation.*;

/**
 * @author : JQK
 * @date : 2021-04-19 22:09
 * @description :
 */
@RestController
public class ChuChaiController {

    private final ChuChaiService chuChaiService;

    public ChuChaiController(ChuChaiService chuChaiService) {
        this.chuChaiService = chuChaiService;
    }

    /**
     * 提交出差记录
     * @param chuChai 出差信息
     * @return
     */
    @PostMapping(value = "/chuchai")
    public ResponseBody addChuChai(@RequestBody ChuChaiBo chuChai) {
        boolean addChuChai = chuChaiService.addChuChai(chuChai);
        return ResponseBody.getInstance(ErrorCode.OK, "提交成功");
    }

    /**
     * 修改出差记录
     * @param chuChai
     * @return
     */
    @PutMapping(value = "/chuchai")
    public ResponseBody updateChuChai(@RequestBody ChuChaiBo chuChai) {
        boolean update = chuChaiService.updateChuChai(chuChai);
        return ResponseBody.getInstance(ErrorCode.OK, "修改成功");
    }

    /**
     * 删除出差记录
     * @param id 出差记录id
     * @return
     */
    @DeleteMapping(value = "/chuchai")
    public ResponseBody removeChuChai(String id) {
        boolean remove = chuChaiService.removeChuChai(id);
        return ResponseBody.getInstance(ErrorCode.OK, "删除成功");
    }

    /**
     * 批量删除出差记录
     * @param ids 多个出差记录id，以 ,分隔
     * @return
     */
    @DeleteMapping(value = "/chuchais")
    public ResponseBody removeChuChais(String ids) {
        boolean remove = chuChaiService.removeChuChais(ids);
        return ResponseBody.getInstance(ErrorCode.OK, "删除成功");
    }

    /**
     * 查看出差记录，为个人查看自己的出差记录
     * @return
     */
    public ResponseBody getChuChai() {


        return null;
    }

}
