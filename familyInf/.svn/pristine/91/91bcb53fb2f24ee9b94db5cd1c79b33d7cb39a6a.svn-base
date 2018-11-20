package com.nn.dao;

import com.nn.bean.Lingqian;
import com.nn.bean.LingqianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LingqianMapper {
    long countByExample(LingqianExample example);

    int deleteByExample(LingqianExample example);

    int deleteByPrimaryKey(String id);

    int insert(Lingqian record);

    int insertSelective(Lingqian record);

    List<Lingqian> selectByExample(LingqianExample example);

    Lingqian selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Lingqian record, @Param("example") LingqianExample example);

    int updateByExample(@Param("record") Lingqian record, @Param("example") LingqianExample example);

    int updateByPrimaryKeySelective(Lingqian record);

    int updateByPrimaryKey(Lingqian record);
}