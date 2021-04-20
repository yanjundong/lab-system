package com.coder.labsystem.model.query;

/**
 * @author : JQK
 * @date : 2021-04-19 20:52
 * @description : 用户信息的过滤查询
 */
public class UserQuery extends PageQuery {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
