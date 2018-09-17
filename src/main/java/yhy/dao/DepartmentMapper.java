package yhy.dao;

import yhy.pojo.Department;

/**
 * @Author: yhy
 * @Date: 2018/9/12 17:28
 * @Version 1.0
 */
public interface DepartmentMapper {
    /*使用user分布查询 getDepartmentById*/
    public Department getDepartmentById(Integer id);

    public Department getDepartmentByIdPlus(Integer id);

    /*使用部门分布查询 getDepartmentByIdStep*/
    public Department getDepartmentByIdStep(Integer id);
}
