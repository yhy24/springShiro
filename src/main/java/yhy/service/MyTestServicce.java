package yhy.service;

import yhy.bo.DeleteConditionBO;
import yhy.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: yhy
 * @Date: 2018/9/13 10:15
 * @Version 1.0
 */
public interface MyTestServicce {
    /*动态sql的使用*/
/*if和where标签的使用*/
    public List<User> getUsesByConditionsIf(User user);

    /*trim标签的使用*/
    public List<User> getUsesByConditionsTrim(User user);

    /*choose标签的使用*/
    public List<User> getUsesByConditionsChoose(User user);

    /*set标签的使用*/
    public void updateUserById(User user);
/*使用foreach进行遍历使用*/
    public List<User> getUsesByConditionsForeach(List<Integer> ids);

    public List<User> getUsesByConditions();
/*批量删除*/
    public void deteUsersByDeleteConditions(DeleteConditionBO deleteConditionBO);
/*批量查找*/
    public List<User> getUsersByConditions(DeleteConditionBO deleteConditionBO);
}
