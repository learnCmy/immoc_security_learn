package com.imooc.pojo;

import java.util.Date;

/**
 * @Auther: cmy
 * @Date: 2018/9/15 11:19
 * @Description:
 */
public abstract class BasePojo {

    private Date created;
    private Date updated;
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}