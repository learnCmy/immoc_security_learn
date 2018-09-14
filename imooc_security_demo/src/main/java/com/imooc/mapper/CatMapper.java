package com.imooc.mapper;

import com.imooc.pojo.Cat;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.SpecialProvider;

public interface CatMapper extends MyMapper<Cat> {

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertSelective(Cat cat);
}