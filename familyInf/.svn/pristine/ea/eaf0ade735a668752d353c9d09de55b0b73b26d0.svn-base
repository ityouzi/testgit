package com.nn.dao;

import com.nn.bean.Shenqing;
import com.nn.bean.ShenqingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShenqingMapper {
    long countByExample(ShenqingExample example);

    int deleteByExample(ShenqingExample example);

    int deleteByPrimaryKey(String id);

    int insert(Shenqing record);

    int insertSelective(Shenqing record);

    List<Shenqing> selectByExample(ShenqingExample example);

    Shenqing selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Shenqing record, @Param("example") ShenqingExample example);

    int updateByExample(@Param("record") Shenqing record, @Param("example") ShenqingExample example);

    int updateByPrimaryKeySelective(Shenqing record);

    int updateByPrimaryKey(Shenqing record);
}