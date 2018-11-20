package com.nn.dao;

import com.nn.bean.Said;
import com.nn.bean.SaidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaidMapper {
    long countByExample(SaidExample example);

    int deleteByExample(SaidExample example);

    int deleteByPrimaryKey(String saidId);

    int insert(Said record);

    int insertSelective(Said record);

    List<Said> selectByExample(SaidExample example);

    Said selectByPrimaryKey(String saidId);

    int updateByExampleSelective(@Param("record") Said record, @Param("example") SaidExample example);

    int updateByExample(@Param("record") Said record, @Param("example") SaidExample example);

    int updateByPrimaryKeySelective(Said record);

    int updateByPrimaryKey(Said record);
}