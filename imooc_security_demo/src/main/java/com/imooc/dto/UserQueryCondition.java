package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class UserQueryCondition {
    @NotEmpty
    @ApiModelProperty(value = "用户名字不能为空")
    private String username;

    private int age;

    private int agetTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgetTo() {
        return agetTo;
    }

    public void setAgetTo(int agetTo) {
        this.agetTo = agetTo;
    }
}
