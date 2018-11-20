package com.nn.dao;

import com.nn.bean.Festival;
import com.nn.bean.FestivalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FestivalMapper {
    long countByExample(FestivalExample example);

    int deleteByExample(FestivalExample example);

    int deleteByPrimaryKey(Integer festivalId);

    int insert(Festival record);

    int insertSelective(Festival record);

    List<Festival> selectByExample(FestivalExample example);

    Festival selectByPrimaryKey(Integer festivalId);

    int updateByExampleSelective(@Param("record") Festival record, @Param("example") FestivalExample example);

    int updateByExample(@Param("record") Festival record, @Param("example") FestivalExample example);

    int updateByPrimaryKeySelective(Festival record);

    int updateByPrimaryKey(Festival record);
}