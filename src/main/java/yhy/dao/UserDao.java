package yhy.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import yhy.pojo.Department;
import yhy.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserDao {
    public List<User> findUser();
    public User findOneUser(@Param("username") String username);
//    public List<User> findUserPage();

    public int saveBath(List<User> lists);

    public int insertOne(User user);

    public List<User> getUsersByDeptId(Integer id);

    public List<User> getUsers(Department department);

    public void deleteIds(User user);

    public List<String> getByFor(@Param("ids") List<Long> ids,@Param("gender") String gender);

    public void insertInto(List<User> users);

    public User testUser(Integer id);

    public List<User> usersTest(@Param("ids") List<Long> ids);

    public Page<User> getUserList();

    public List<User> testDate(User user);

    public String getMaxCode(@Param("codPre") String codPre);

    public List<User> selectUserByTime(User user);
    public List<User> selectUserByTime1();
}
