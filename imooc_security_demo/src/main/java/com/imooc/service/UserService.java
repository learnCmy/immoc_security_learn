package com.imooc.service;

import com.github.pagehelper.PageHelper;
import com.imooc.dto.User;
import com.imooc.mapper.CatMapper;
import com.imooc.pojo.Cat;
import com.imooc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService extends BaseService<TbUser>{
    @Autowired
    private CatMapper catMapper;

    public List<Cat> getCatList(String name) {
        PageHelper.startPage(1, 3);
        Example example = new Example(Cat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("catName", name);
        List<Cat> cats = catMapper.selectByExample(example);
        return cats;
    }

    @Transactional
    public Integer saveCat(Cat cat) {
        catMapper.insertUseGeneratedKeys(cat);
        //catMapper.insertSelective(cat);
        //catMapper.insert(cat);
        Integer id = cat.getId();
        System.out.println("主键的值是" + id);
        //saveCat2();
        return id;
    }

    @Transactional
    public Integer saveCat2() {
        Cat cat=new Cat();
        cat.setCatAge(9999);
        cat.setCatName("黄城3333");
        catMapper.insert(cat);
        throw new RuntimeException();
    }



}
