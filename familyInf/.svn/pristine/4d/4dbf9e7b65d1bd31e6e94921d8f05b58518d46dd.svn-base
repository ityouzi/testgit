package com.nn.dao;

import com.nn.bean.Simiao;
import com.nn.bean.SimiaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SimiaoMapper {
    long countByExample(SimiaoExample example);

    int deleteByExample(SimiaoExample example);

    int deleteByPrimaryKey(String id);

    int insert(Simiao record);

    int insertSelective(Simiao record);

    List<Simiao> selectByExampleWithBLOBs(SimiaoExample example);

    List<Simiao> selectByExample(SimiaoExample example);

    Simiao selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Simiao record, @Param("example") SimiaoExample example);

    int updateByExampleWithBLOBs(@Param("record") Simiao record, @Param("example") SimiaoExample example);

    int updateByExample(@Param("record") Simiao record, @Param("example") SimiaoExample example);

    int updateByPrimaryKeySelective(Simiao record);

    int updateByPrimaryKeyWithBLOBs(Simiao record);

    int updateByPrimaryKey(Simiao record);
}