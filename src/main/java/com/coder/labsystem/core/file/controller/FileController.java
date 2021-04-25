package com.coder.labsystem.core.file.controller;

import com.coder.labsystem.core.file.service.FileService;
import com.coder.labsystem.model.entity.File;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2021-04-22 15:23
 * @description : 系统中的文件存储，该文档中保存的文件小于 16MB，如果大于该值的话 要使用 Mongo 提供的 GridFS
 */
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/file")
    public ResponseBody addFile(@RequestParam("file") MultipartFile multipartFile) {
        String id = fileService.addFile(multipartFile);
        Map<String, String> data = new HashMap<>(2);
        data.put("fileID", id);
        return ResponseBody.getInstance(ErrorCode.OK, "上传成功", data);
    }

    /**
     * 获取文件
     *
     * @param id 文件的id
     * @param response
     */
    @GetMapping("/file/{id}")
    public void getFile(@PathVariable("id") String id, HttpServletResponse response) {
        File file = fileService.getFile(id);
        response.setContentType(file.getType());
        response.setContentLengthLong(file.getSize());
        String fileName = new String(file.getOriginalName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        /* 浏览器预览 */
        response.setHeader("Content-Disposition", "inline; filename= " + fileName);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(file.getData());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
