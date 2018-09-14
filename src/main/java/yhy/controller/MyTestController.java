package yhy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yhy.bo.DeleteConditionBO;
import yhy.pojo.User;
import yhy.service.MyTestServicce;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: yhy
 * @Date: 2018/9/13 10:12
 * @Version 1.0
 */
@RestController
public class MyTestController {

    @Autowired
    MyTestServicce myTestServicce;

    @RequestMapping("/selectIf")
    @ResponseBody
    public String testIf() {
        User user = new User();
//        user.setId(2);
        user.setEmail("zs@qq.com");
        user.setSexly("girl");
        List<User> usrs = myTestServicce.getUsesByConditionsIf(user);
        return usrs.toString();
    }

    @RequestMapping("/selectTrim")
    @ResponseBody
    public String testTrim() {
        User user = new User();
//        user.setId(2);
        user.setEmail("zs@qq.com");
        user.setSexly("girl");
        List<User> usrs = myTestServicce.getUsesByConditionsTrim(user);
        return usrs.toString();
    }

    @RequestMapping("/selectChoose")
    @ResponseBody
    public String testChoose() {
        User user = new User();
//        user.setId(2);
        user.setEmail("zs@qq.com");
        user.setSexly("girl");
        List<User> usrs = myTestServicce.getUsesByConditionsChoose(user);
        return usrs.toString();
    }

    @RequestMapping("/updateSet")
    @ResponseBody
    public void testSet() {
        User user = new User();
        user.setId(9);
        user.setUsername("露娜");
        user.setEmail("luna@qq.com");
        user.setAge(18);
        user.setSexly("girl");
        myTestServicce.updateUserById(user);
    }
    @RequestMapping("/selectForeach")
    @ResponseBody
    public String testforEach() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(6);
        ids.add(9);
        List<User> userList = myTestServicce.getUsesByConditionsForeach(ids);
        for (User user : userList) {
            System.out.println(user.toString());
        }
        return "----foreach---".toString();
    }

    @RequestMapping("/selectfor")
    @ResponseBody
    public String testforfor() {
        List<User> userList = myTestServicce.getUsesByConditions();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        return "----lsit---".toString();
    }

    @RequestMapping("/deleteForeach")
    @ResponseBody
    public String testdelete() {
        DeleteConditionBO deleteConditionBO = new DeleteConditionBO();
        List<Integer> lists = new ArrayList<>();
        lists.add(105);
        lists.add(107);
        deleteConditionBO.setCode("248");
        deleteConditionBO.setIds(lists);
        myTestServicce.deteUsersByDeleteConditions(deleteConditionBO);
        System.out.println("------删除成功------");
        return "----删除成功---".toString();
    }

    @RequestMapping("/selectCon")
    @ResponseBody
    public String testUsers() {
        DeleteConditionBO deleteConditionBO = new DeleteConditionBO();
        List<Integer> lists = new ArrayList<>();
        lists.add(100);
        lists.add(101);
        lists.add(106);
        deleteConditionBO.setIds(lists);
        deleteConditionBO.setCode("b248");
        List<User> users = myTestServicce.getUsersByConditions(deleteConditionBO);
        /*过滤的使用*/
        List<Integer> collect1 = users.stream().filter(e -> e.getAge() > 20).map(e -> e.getId()).collect(Collectors.toList());
        /*不用过滤的使用*/
        List<Integer> collects = users.stream().map(e -> (e.getId())).collect(Collectors.toList());
        for (Integer collect : collect1) {
            System.out.println("-----id-------"+collect);
        }
        for (User user : users) {
            System.out.println("---user:----"+user);
        }
        return users.toString();
    }
}
