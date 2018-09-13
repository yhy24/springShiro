package yhy.pojo;

import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/9/12 16:19
 * @Version 1.0
 */
public class Department {

    private Integer id;

    private String deptName;
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", users=" + users +
                '}';
    }
}
