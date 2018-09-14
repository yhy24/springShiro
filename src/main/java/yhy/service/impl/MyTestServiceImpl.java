package yhy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yhy.bo.DeleteConditionBO;
import yhy.dao.DynamicMapper;
import yhy.pojo.User;
import yhy.service.MyTestServicce;

import java.util.List;
import java.util.Map;

/**
 * @Author: yhy
 * @Date: 2018/9/13 10:16
 * @Version 1.0
 */
@Service
public class MyTestServiceImpl implements MyTestServicce {

    @Autowired
    DynamicMapper dynamicMapper;
    @Override
    public List<User> getUsesByConditionsIf(User user) {
        return dynamicMapper.getUsesByConditionsIf(user);
    }

    @Override
    public List<User> getUsesByConditionsTrim(User user) {
        return dynamicMapper.getUsesByConditionsTrim(user);
    }

    @Override
    public List<User> getUsesByConditionsChoose(User user) {
        return dynamicMapper.getUsesByConditionsChoose(user);
    }

    @Override
    public void updateUserById(User user) {
        dynamicMapper.updateUserById(user);
    }

    @Override
    public List<User> getUsesByConditionsForeach(List<Integer> ids) {
        return dynamicMapper.getUsesByConditionsForeach(ids);
    }

    @Override
    public List<User> getUsesByConditions() {
        return dynamicMapper.getUsesByConditions();
    }

    @Override
    public void deteUsersByDeleteConditions(DeleteConditionBO deleteConditionBO) {
        dynamicMapper.deteUsersByDeleteConditions(deleteConditionBO);
    }

    @Override
    public List<User> getUsersByConditions(DeleteConditionBO deleteConditionBO) {
        return dynamicMapper.getUsersByConditions(deleteConditionBO);
    }

}
