package com.lhj.project.service.impl;

import com.lhj.project.dao.INotifyLogDao;
import com.lhj.project.model.NotifyLog;
import com.lhj.project.service.INotifyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * describe
 * author liuhj18
 * Date 2018/6/8
 * version 1.00
 */
@Service("notufyLogService")
public class NotufyLogServiceImpl implements INotifyLogService {

    @Autowired
    private INotifyLogDao notifyLogDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return notifyLogDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(NotifyLog record) {
        return notifyLogDao.insert(record);
    }

    @Override
    public int insertSelective(NotifyLog record) {
        return notifyLogDao.insertSelective(record);
    }

    @Override
    public NotifyLog selectByPrimaryKey(Integer id) {
        return notifyLogDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NotifyLog record) {
        return notifyLogDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NotifyLog record) {
        return notifyLogDao.updateByPrimaryKeySelective(record);
    }
}
