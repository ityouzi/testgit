package com.nn.dao;

import com.nn.bean.Biaoqian;
import com.nn.bean.BiaoqianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BiaoqianMapper {
    long countByExample(BiaoqianExample example);

    int deleteByExample(BiaoqianExample example);

    int deleteByPrimaryKey(String id);

    int insert(Biaoqian record);

    int insertSelective(Biaoqian record);

    List<Biaoqian> selectByExample(BiaoqianExample example);

    Biaoqian selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Biaoqian record, @Param("example") BiaoqianExample example);

    int updateByExample(@Param("record") Biaoqian record, @Param("example") BiaoqianExample example);

    int updateByPrimaryKeySelective(Biaoqian record);

    int updateByPrimaryKey(Biaoqian record);
}