package com.imooc.dto;

import lombok.Getter;


@Getter
public enum ActionTypeEnum {
    INSERT(0,"插入"),
    UPDATE(1,"更新"),
    DELETE(2,"删除");
    private Integer code;
    private String message;

    ActionTypeEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
