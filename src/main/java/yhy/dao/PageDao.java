package yhy.dao;

import yhy.pojo.User;

/**
 * @Author: yhy
 * @Date: 2018/9/12 15:45
 * @Version 1.0
 */
public interface PageDao {

    public User getUserById(Integer id);
    public User getUserByEId(Integer id);
    /*测试分布查询*/
    public User getUserByIdStep(Integer id);
}
