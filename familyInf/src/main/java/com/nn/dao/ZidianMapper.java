package com.nn.dao;

import com.nn.bean.Zidian;
import com.nn.bean.ZidianExample;
import com.nn.bean.ZidianWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZidianMapper {
    long countByExample(ZidianExample example);

    int deleteByExample(ZidianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZidianWithBLOBs record);

    int insertSelective(ZidianWithBLOBs record);

    List<ZidianWithBLOBs> selectByExampleWithBLOBs(ZidianExample example);

    List<Zidian> selectByExample(ZidianExample example);

    ZidianWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZidianWithBLOBs record, @Param("example") ZidianExample example);

    int updateByExampleWithBLOBs(@Param("record") ZidianWithBLOBs record, @Param("example") ZidianExample example);

    int updateByExample(@Param("record") Zidian record, @Param("example") ZidianExample example);

    int updateByPrimaryKeySelective(ZidianWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ZidianWithBLOBs record);

    int updateByPrimaryKey(Zidian record);
}