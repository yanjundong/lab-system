package com.coder.labsystem.core.fapiao.controller;

import com.coder.labsystem.core.fapiao.service.FaPiaoService;
import com.coder.labsystem.model.bo.FaPiaoBo;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import com.coder.labsystem.model.query.FaPiaoQuery;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : JQK
 * @date : 2021-04-21 10:45
 * @description :
 */
@RestController
public class FaPiaoController {

    private final FaPiaoService faPiaoService;
    public FaPiaoController(FaPiaoService faPiaoService) {
        this.faPiaoService = faPiaoService;
    }

    /**
     * 上传发票相关表单
     * @param faPiaoBo
     * @return
     */
    @PostMapping("/fapiao")
    public ResponseBody addFaPiao(@RequestBody @Valid FaPiaoBo faPiaoBo, BindingResult bindingResult) {
        boolean b = faPiaoService.addFaPiao(faPiaoBo);
        return ResponseBody.getInstance(ErrorCode.OK, "上传成功");
    }

    /**
     * 获取个人的发票上传情况
     * @return
     */
    @PostMapping(value = "/fapiaos")
    public ResponseBody listFaPiao(@RequestBody FaPiaoQuery query) {

        return null;
    }

    /**
     * 修改发票中的内容
     * @return
     */
    @PutMapping(value = "/fapiao/{id}")
    public ResponseBody updateFaPiao(@PathVariable("id")String id, @RequestBody FaPiaoBo faPiaoBo) {

        return null;
    }

    /**
     * 删除单个发票提交记录
     * @return
     */
    @DeleteMapping(value = "/fapiao/{id}")
    public ResponseBody removeFaPiao(@PathVariable("id")String id) {

        return null;
    }

    /**
     * 批量删除发票提交记录
     * @return
     */
    @DeleteMapping(value = "/fapiaos/{ids}")
    public ResponseBody removeFaPiaos(@PathVariable("ids")String ids) {

        return null;
    }


}
