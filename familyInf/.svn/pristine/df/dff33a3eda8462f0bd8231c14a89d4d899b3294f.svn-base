package com.nn.dao;

import com.nn.bean.Cwdy;
import com.nn.bean.CwdyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CwdyMapper {
    long countByExample(CwdyExample example);

    int deleteByExample(CwdyExample example);

    int deleteByPrimaryKey(String cwdyid);

    int insert(Cwdy record);

    int insertSelective(Cwdy record);

    List<Cwdy> selectByExample(CwdyExample example);

    Cwdy selectByPrimaryKey(String cwdyid);

    int updateByExampleSelective(@Param("record") Cwdy record, @Param("example") CwdyExample example);

    int updateByExample(@Param("record") Cwdy record, @Param("example") CwdyExample example);

    int updateByPrimaryKeySelective(Cwdy record);

    int updateByPrimaryKey(Cwdy record);
}