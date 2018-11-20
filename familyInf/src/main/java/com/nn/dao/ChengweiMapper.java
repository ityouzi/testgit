package com.nn.dao;

import com.nn.bean.Chengwei;
import com.nn.bean.ChengweiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChengweiMapper {
    long countByExample(ChengweiExample example);

    int deleteByExample(ChengweiExample example);

    int deleteByPrimaryKey(Integer chengweiId);

    int insert(Chengwei record);

    int insertSelective(Chengwei record);

    List<Chengwei> selectByExample(ChengweiExample example);

    Chengwei selectByPrimaryKey(Integer chengweiId);

    int updateByExampleSelective(@Param("record") Chengwei record, @Param("example") ChengweiExample example);

    int updateByExample(@Param("record") Chengwei record, @Param("example") ChengweiExample example);

    int updateByPrimaryKeySelective(Chengwei record);

    int updateByPrimaryKey(Chengwei record);
}