package com.lhj.project.service;

import com.lhj.project.model.NotifyLog;

/**
 * describe
 * author liuhj18
 * Date 2018/6/8
 * version 1.00
 */
public interface INotifyLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(NotifyLog record);

    int insertSelective(NotifyLog record);

    NotifyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NotifyLog record);

    int updateByPrimaryKey(NotifyLog record);
}
