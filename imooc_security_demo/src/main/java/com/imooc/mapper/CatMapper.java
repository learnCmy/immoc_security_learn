package com.imooc.mapper;

import com.imooc.pojo.Cat;
import com.imooc.utils.MyMapper;

public interface CatMapper extends MyMapper<Cat> {

    /*@Override
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertSelective(Cat cat);*/

}