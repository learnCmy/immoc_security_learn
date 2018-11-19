package com.imooc.dto;

import lombok.Data;

@Data
public class ChangeItem {

    private String field;

    private String fieldShowName;

    private String oldValue;

    private String newValue;
}
