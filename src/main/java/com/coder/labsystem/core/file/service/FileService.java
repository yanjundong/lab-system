package com.coder.labsystem.core.file.service;

import com.coder.labsystem.core.file.repository.FileRepository;
import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.exception.FileAccessException;
import com.coder.labsystem.exception.FileNotFoundException;
import com.coder.labsystem.model.entity.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author : JQK
 * @date : 2021-04-22 15:24
 * @description :
 */
@Service
public class FileService {
    private final static Logger LOG = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * 保存文件数据到mongo中
     * @param multipartFile
     * @return
     */
    public String addFile(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        long size = multipartFile.getSize();
        byte[] data = null;
        try {
            data = multipartFile.getBytes();
        } catch (IOException e) {
            LOG.error("获取 {} 数据时错误", originalFilename, e);
            throw new FileAccessException("获取文件数据错误", e);
        }

        File file = new File(contentType, originalFilename, size, data);
        File insert = fileRepository.insert(file);
        if (insert == null) {
            throw new DaoException("保存文件数据错误");
        }
        return file.getId();
    }

    /**
     * 通过文件id查找该文件
     * @param id 文件id
     * @return
     */
    public File getFile(String id) {
        Optional<File> optional = fileRepository.findById(id);
        if (!optional.isPresent()) {
            throw new FileNotFoundException("没有找到这个文件");
        }
        return optional.get();
    }
}
