package com.imooc.mapper;

import com.imooc.pojo.Aa;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AaMapper extends MyMapper<Aa> {

    @Select("select * from aa,cat")
    List<Aa> selectMulitple();

}