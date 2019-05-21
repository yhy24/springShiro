package yhy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yhy.pojo.Department;
import yhy.pojo.User;
import yhy.service.UserService;

import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping(value = "/saveOne",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody User user) {
        User user1 = user;
        if (user1 != null) {
            System.out.println("没有参数额!");
        } else {
            System.out.println(user.toString());
        }

        /*User user = new User();
        user.setPhone("9659999");
        user.setAge(20);
        user.setEmail("hao@qq.com");
        user.setUsername("ying");
        user.setPassword("464655");
        System.out.println("-----------one------");*/
        int i = userService.insertOne(user);
        System.out.println(user.getId()+"-------------");
        System.out.println("-----userId------"+user.getId());
        return "-------one-------";
    }

    @RequestMapping("/selectOne")
    @ResponseBody
    public String test1() {
        return userService.getUserById(2).toString();
    }
    @RequestMapping("/selectTne")
    @ResponseBody
    public String test2() {
        User userByEId = userService.getUserByEId(2);
        System.out.println(userByEId.getDepartment().toString());
        return userByEId.toString();
    }
    /*通过用户作为分布查询部门*/
    @RequestMapping("/selectStep")
    @ResponseBody
    public String test3() {
        User userByIdStep = userService.getUserByIdStep(2);
        System.out.println("-----user:--------"+userByIdStep.toString());
        System.out.println("--------step:-------" + userByIdStep.getDepartment());
        return userByIdStep.toString();
    }
/*进行javaBean中集合的使用的映射关系的使用*/
    @RequestMapping("/selectList")
    @ResponseBody
    public String test4() {
        Department departmentByIdPlus = userService.getDepartmentByIdPlus(2);
        System.out.println("-----user:--------"+departmentByIdPlus.toString());
        System.out.println("--------step:-------" + departmentByIdPlus.getUsers().toString());
        return departmentByIdPlus.toString();
    }
/*通过部门分布查询用户*/
    @RequestMapping("/selectDeptList")
    @ResponseBody
    public String test5() {
        Department departmentByIdStep = userService.getDepartmentByIdStep(2);
        System.out.println("-----departmentByIdPlus:--------"+departmentByIdStep.toString());
        System.out.println("--------listS:-------" + departmentByIdStep.getUsers().toString());
        return departmentByIdStep.toString();
    }

   /* public static void main(String[] args) {
        User user = new User();
        user.setPhone("9659999");
        user.setAge(22);
        user.setEmail("yan@qq.com");
        user.setUsername("yan");
        user.setPassword("1235");
        user.setCode("123");
        user.setDeptId(2);
        user.setSexly("b");
        user.setCreateDate(new Date());
        user.setInterest("篮球");
        user.setModifyDate(new Date());
        user.setMark("test just");
        Department department = new Department();
        department.setDeptName("test");
        user.setDepartment(department);
        user.setId(16);
        System.out.println(JSON.toJSONString(user));
    }*/


    /*通过部门分布查询用户*/
    @RequestMapping("/test7")
    @ResponseBody
    public String test7() {
        Department departmentByIdStep = userService.getDepartmentByIdStep(2);
        System.out.println("-----departmentByIdPlus:--------"+departmentByIdStep.toString());
        System.out.println("--------listS:-***********------" + departmentByIdStep.getUsers().toString());
        return departmentByIdStep.toString();
    }
}
