package yhy.dao;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import yhy.pojo.Department;
import yhy.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserDao {
    public List<User> findUser();
    public User findOneUser(@Param("username") String username);
    public List<User> findUserPage();

    public int saveBath(List<User> lists);

    public int insertOne(User user);

    public List<User> getUsersByDeptId(Integer id);

    public List<User> getUsers(Department department);


}
