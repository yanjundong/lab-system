package com.coder.labsystem.core.baoxiao.controller;

import com.coder.labsystem.model.http.ResponseBody;
import com.coder.labsystem.model.query.BaoXiaoQuery;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

/**
 * @author : JQK
 * @date : 2021-04-22 17:51
 * @description : 管理员管理 出差报销和发票报销
 *
 * 管理员报销时的流程：
 * 1. 查询出该月需要报销的用户、金额等信息；
 * 2. 管理员也可以根据用户名查询到用户的详细出差信息、提交发票记录等详情；
 * 3. 如果管理员人员某条报销记录不合格，也可以删除或修改；
 * 4. 之后重新获取该月的需报销情况；
 */
@RestController
public class BaoXiaoController {
    /**
     * 查询出该月需要报销的用户、金额等信息
     * @param yearMonth 要查询的年月，默认为当月
     * @return
     */
    @GetMapping(value = "/baoxiao")
    public ResponseBody listBaoXiaoMoney(@RequestParam(required = false) YearMonth yearMonth) {

        return null;
    }

    /**
     * 按用户名查询报销情况——出差记录、发票提交记录
     * @param username
     * @param baoXiaoQuery
     * @return
     */
    @PostMapping(value = "/baoxiao/{username}")
    public ResponseBody listBaoXiaoByUser(@PathVariable("username")String username, @RequestBody BaoXiaoQuery baoXiaoQuery) {

        return null;
    }

    /**
     * 确定某月（如果不指定，为当前月）的所有记录已报销
     * @param yearMonth 要修改的年月，默认为当月
     * @return
     */
    @PutMapping(value = "/baoxiao")
    public ResponseBody updateBaoXiao(@RequestParam(required = false) YearMonth yearMonth) {

        return null;
    }

}
