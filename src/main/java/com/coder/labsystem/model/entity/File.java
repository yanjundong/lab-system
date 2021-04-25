package com.coder.labsystem.model.entity;

import cn.hutool.core.util.IdUtil;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author : JQK
 * @date : 2021-04-22 11:37
 * @description : 系统中的文件存储，该文档中保存的文件小于 16MB，如果大于该值的话 要使用 Mongo 提供的 GridFS
 */
@Document("file")
public class File {

    /** 数据库中保存时的唯一ID */
    @MongoId(value = FieldType.STRING)
    @Field(value = "id")
    private String id;

    /** 该文件的类型 */
    private String type;

    /** 该文件的原名称 */
    private String originalName;

    /** 该文件的大小 */
    private long size;

    /** 该文件的数据 */
    private byte[] data;

    public File() {
        this.id = IdUtil.objectId();
    }

    public File(String type, String originalName, long size, byte[] data) {
        this();
        this.type = type;
        this.originalName = originalName;
        this.size = size;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
