package com.nn.dao;

import com.nn.bean.Pingfen;
import com.nn.bean.PingfenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PingfenMapper {
    long countByExample(PingfenExample example);

    int deleteByExample(PingfenExample example);

    int deleteByPrimaryKey(String id);

    int insert(Pingfen record);

    int insertSelective(Pingfen record);

    List<Pingfen> selectByExample(PingfenExample example);

    Pingfen selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Pingfen record, @Param("example") PingfenExample example);

    int updateByExample(@Param("record") Pingfen record, @Param("example") PingfenExample example);

    int updateByPrimaryKeySelective(Pingfen record);

    int updateByPrimaryKey(Pingfen record);
}