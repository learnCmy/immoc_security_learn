package com.imooc.mapper;

import com.imooc.utils.UniqueIdGenerator;
import tk.mybatis.mapper.genid.GenId;

/**
 * @Auther: cmy
 * @Date: 2018/9/10 16:25
 * @Description:
 */
public class MyGenId implements GenId<Integer> {
    @Override
    public Integer genId(String table, String column) {
        //IdWorker idWorker = new IdWorker();
       return new Integer(UniqueIdGenerator.generateRandomNumStr(6));

    }
}
