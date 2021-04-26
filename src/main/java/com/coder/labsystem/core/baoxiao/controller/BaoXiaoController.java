package com.coder.labsystem.core.baoxiao.controller;

import com.coder.labsystem.core.baoxiao.service.BaoXiaoService;
import com.coder.labsystem.model.bo.ChuChaiBaoXiaoBO;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import com.coder.labsystem.model.query.ChuChaiQuery;
import com.coder.labsystem.model.vo.PageResp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-22 17:51
 * @description : 管理员管理 出差报销和发票报销
 *
 */
@RestController
public class BaoXiaoController {

    private final BaoXiaoService baoXiaoService;

    public BaoXiaoController(BaoXiaoService baoXiaoService) {
        this.baoXiaoService = baoXiaoService;
    }

    /**
     * 出差的报销，按用户列出某月需要报销的金额，不做分页处理
     * @param yearMonth
     * @return
     */
    @GetMapping(value = "/baoxiao/chuchai")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseBody listChuChaiBaoXiaoMoney(@RequestParam(required = false) YearMonth yearMonth) {
        List<ChuChaiBaoXiaoBO> baoXiaoBOList = baoXiaoService.listChuChaiBaoXiaoMoney(yearMonth);
        return ResponseBody.getInstance(ErrorCode.OK, "查询成功", baoXiaoBOList);
    }

    /**
     * 列出某人某月的出差报销情况
     * @param username
     * @param chuChaiQuery
     * @return
     */
    @PostMapping(value = "/baoxiao/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseBody listChuChaiBaoXiaoByUsername(@PathVariable(value = "username")String username,
                                                     @RequestBody ChuChaiQuery chuChaiQuery) {
        PageResp pageResp = baoXiaoService.listChuChaiBaoXiaoByUsername(username, chuChaiQuery);
        return ResponseBody.getInstance(ErrorCode.OK, "查询成功", pageResp);
    }

    /**
     * 确定已报销某月的所有出差
     * TODO 目前出差报销不在数据库中记录
     * @param yearMonth 如果没有该参数，则为上个月
     * @return
     */
    @PutMapping(value = "/baoxiao/state")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Deprecated
    public ResponseBody updateChuChaiStateByYearMonth(@RequestParam(required = false)YearMonth yearMonth) {
        boolean b = baoXiaoService.baoXiaoChuChai(yearMonth);
        return ResponseBody.getInstance(ErrorCode.OK, "报销成功");
    }
}
