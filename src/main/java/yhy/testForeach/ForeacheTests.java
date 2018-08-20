package yhy.testForeach;

import yhy.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        lists.add(users);
        /*使用forEach方式*/
        lists.forEach(e -> {
            System.out.println(e.getEmail());
        });
        System.out.println(lists.stream().map(demo -> demo.getEmail()).collect(Collectors.toList()));
        /*使用stream().map方法*/
        List<String> collect = lists.stream().map(demo -> demo.getEmail()).collect(Collectors.toList());
        System.out.println(collect.get(0));
    }
}
