package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "action")
public class Action {

    @Id
    private Long id;

    //实体Id
    private Long objectId;

    //实体对象
    private String objectClass;

    //操作者
    private String operator;

    //操作时间
    private Date operateTime;

    //操作动作(1新增,2跟新,3删除)
    private Integer actionType;

    //改变的内容（存json）-->List<ChangeItem>
    private String changeItem;
}
