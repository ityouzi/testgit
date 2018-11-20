package com.nn.dao;

import com.nn.bean.Memorialday;
import com.nn.bean.MemorialdayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemorialdayMapper {
    long countByExample(MemorialdayExample example);

    int deleteByExample(MemorialdayExample example);

    int deleteByPrimaryKey(String fid);

    int insert(Memorialday record);

    int insertSelective(Memorialday record);

    List<Memorialday> selectByExample(MemorialdayExample example);

    Memorialday selectByPrimaryKey(String fid);

    int updateByExampleSelective(@Param("record") Memorialday record, @Param("example") MemorialdayExample example);

    int updateByExample(@Param("record") Memorialday record, @Param("example") MemorialdayExample example);

    int updateByPrimaryKeySelective(Memorialday record);

    int updateByPrimaryKey(Memorialday record);
}