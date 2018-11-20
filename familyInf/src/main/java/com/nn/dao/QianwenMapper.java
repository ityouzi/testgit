package com.nn.dao;

import com.nn.bean.Qianwen;
import com.nn.bean.QianwenExample;
import com.nn.bean.QianwenWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QianwenMapper {
    long countByExample(QianwenExample example);

    int deleteByExample(QianwenExample example);

    int deleteByPrimaryKey(String id);

    int insert(QianwenWithBLOBs record);

    int insertSelective(QianwenWithBLOBs record);

    List<QianwenWithBLOBs> selectByExampleWithBLOBs(QianwenExample example);

    List<Qianwen> selectByExample(QianwenExample example);

    QianwenWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") QianwenWithBLOBs record, @Param("example") QianwenExample example);

    int updateByExampleWithBLOBs(@Param("record") QianwenWithBLOBs record, @Param("example") QianwenExample example);

    int updateByExample(@Param("record") Qianwen record, @Param("example") QianwenExample example);

    int updateByPrimaryKeySelective(QianwenWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QianwenWithBLOBs record);

    int updateByPrimaryKey(Qianwen record);
}