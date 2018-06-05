package com.lhj.project.dao;

import com.lhj.project.model.DashData;

public interface DashDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DashData record);

    int insertSelective(DashData record);

    DashData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DashData record);

    int updateByPrimaryKey(DashData record);
}