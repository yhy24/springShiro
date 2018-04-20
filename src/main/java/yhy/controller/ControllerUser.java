package yhy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yhy.pojo.User;
import yhy.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControllerUser {
    @Resource
    UserService userService;



    @RequestMapping("/login")
    public String test(){
        System.out.println("连接池测试:");
        return "login";
    }
    @RequestMapping("/json")
    @ResponseBody
    public String testJson(){
        return "hello Word！";
    }

    @RequestMapping("/info")
    @ResponseBody
    public String userInfo(){
        List<User> list = userService.getUsers();
        /*控制台查看数据*/
        for(User user :list){
            System.out.println(user.toString());
        }
        return list.toString();
    }


}
