package yhy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlllerGood {

    @RequestMapping("/test101")
    @ResponseBody
    public String test101(){
        System.out.println("测试如何拉去分支");
        return "创造！";
    }

}
