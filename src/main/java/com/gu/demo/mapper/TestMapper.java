package com.gu.demo.mapper;

import com.gu.demo.dto.Info;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: guMingYang
 * @DATE: 2019/5/12
 */
@Repository
public interface TestMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "leadHeadImg", column = "leadHeadImg"),
            @Result(property = "leadAbout", column = "leadAbout"),
            @Result(property = "leadName", column = "leadName")
    })
    @Select("select * from leader")
    List<Map<Object,Object>> query();
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "leadHeadImg", column = "leadHeadImg"),
            @Result(property = "leadAbout", column = "leadAbout"),
            @Result(property = "leadName", column = "leadName")
    })
    @Select("select * from leader")
    List<Info> query1();
}
