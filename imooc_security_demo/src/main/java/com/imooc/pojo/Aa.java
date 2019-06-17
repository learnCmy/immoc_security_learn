package com.imooc.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Aa {
    private Date createTime;

    private Integer userId;

    private BigDecimal amount;

    private Integer buyWay;

    private String  cat_name;

    private Cat cat;


}