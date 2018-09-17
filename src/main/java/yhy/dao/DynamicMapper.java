package yhy.dao;

import org.apache.ibatis.annotations.Param;
import yhy.bo.DeleteConditionBO;
import yhy.pojo.User;

import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/9/13 10:39
 * @Version 1.0
 */
public interface DynamicMapper {

    public List<User> getUsesByConditionsIf(User user);

    public List<User> getUsesByConditionsTrim(User user);

    public List<User> getUsesByConditionsChoose(User user);

    public void updateUserById(User user);

    public List<User> getUsesByConditionsForeach(@Param("ids") List<Integer> ids);

    public List<User> getUsesByConditions();

    public void deteUsersByDeleteConditions(DeleteConditionBO deleteConditionBO);

    public List<User> getUsersByConditions(DeleteConditionBO deleteConditionBO);
}
