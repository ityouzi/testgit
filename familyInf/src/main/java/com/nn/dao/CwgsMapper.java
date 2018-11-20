package com.nn.dao;

import com.nn.bean.Cwgs;
import com.nn.bean.CwgsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CwgsMapper {
    long countByExample(CwgsExample example);

    int deleteByExample(CwgsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cwgs record);

    int insertSelective(Cwgs record);

    List<Cwgs> selectByExample(CwgsExample example);

    Cwgs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cwgs record, @Param("example") CwgsExample example);

    int updateByExample(@Param("record") Cwgs record, @Param("example") CwgsExample example);

    int updateByPrimaryKeySelective(Cwgs record);

    int updateByPrimaryKey(Cwgs record);
}