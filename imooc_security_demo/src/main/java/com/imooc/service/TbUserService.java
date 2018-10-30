package com.imooc.service;

import com.imooc.mapper.TbUserMapper;
import com.imooc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: cmy
 * @Date: 2018/10/23 09:05
 * @Description:
 */
@Service
public class TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sedddapper(){


        TbUser tbUser=new TbUser();
        tbUser.setEmail("dadsad@qq.com");
        tbUser.setPhone("132314151");
        tbUser.setPassword("dafa");
        tbUserMapper.insert(tbUser);

        int i=2/0;

    }
}
