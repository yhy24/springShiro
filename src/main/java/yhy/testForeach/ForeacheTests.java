package yhy.testForeach;

import yhy.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/8/20 16:35
 * @Version 1.0
 */
public class ForeacheTests {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("yang");
        user.setPassword("124");
        user.setEmail("yang@qq.ocm");
        user.setAge(20);
        user.setPhone("987456146");

        User users = new User();
        users.setUsername("hao");
        users.setPhone("rghrehyr");
        users.setEmail("hao@qq.ocm");
        users.setPassword("124");
        users.setAge(56);

        List<User> lists = new ArrayList<>();
        lists.add(user);
        lists.stream().forEach(e -> {
            System.out.println(e.getEmail());
        });
        for (User user1 : lists) {

        }
    }
}
