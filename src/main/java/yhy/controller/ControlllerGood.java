package yhy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlllerGood {

    @RequestMapping("/test101")
    @ResponseBody
    public String test101(){
        System.out.println("测试如何拉取分支");
        String str  = "520";
        if ("520".equals("520")) {
            System.out.println("I love you !");
            str = "I love you";
        } else {
            System.out.println("I hate you");
            str = "I hate you";
        }
        return "创造"+str;
    }

}
