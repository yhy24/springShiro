package yhy.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yhy.pojo.User;
import yhy.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/8/22 17:30
 * @Version 1.0
 */
@Controller
public class PageController {

    @Autowired
    UserService userService;


    @RequestMapping("/page")
    public String testPage() {
        System.out.println("-------1-----");
        int page = 1;
        int size = 2;
        PageInfo<User> userByPage = userService.findUserByPage(page, size);
        System.out.println("------controller-----" + userByPage.getList().toString());
        return userByPage.toString();
    }

    /*批量保存数据*/
    @RequestMapping("/saveb")
    @ResponseBody
    public String testBacth() {
        List<User> lists = new ArrayList<>();
        User user = new User();
        user.setId(102);
        user.setPhone("486365");
        user.setAge(18);
        user.setEmail("ying@qq.com");
        user.setUsername("ying");
        user.setPassword("654");
        User user1 = new User();
        user1.setId(105);
        user1.setPhone("98653");
        user1.setAge(20);
        user1.setEmail("yhy@qq.com");
        user1.setUsername("hao");
        user1.setPassword("963852");
        lists.add(user);
        lists.add(user1);
        long start = System.currentTimeMillis();
        int saveBatch = userService.saveBatch(lists);
        long end = System.currentTimeMillis();
        System.out.println("-----------save------" + (end - start));
        return String.valueOf(saveBatch);
    }

}
