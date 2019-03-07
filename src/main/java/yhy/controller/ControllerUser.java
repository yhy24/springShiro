package yhy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yhy.exception.ServiceException;
import yhy.pojo.Department;
import yhy.pojo.PageBean;
import yhy.pojo.User;
import yhy.service.UserService;
import yhy.util.ExcelUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControllerUser {
    private static Logger logger = Logger.getLogger(ControllerUser.class);

    @Resource
    UserService userService;

    private Subject subject=null;

    @RequestMapping("/login")
    public String test(){
        System.out.println("登录页面!");
        logger.info("logger登录页面");
        return "login";
    }

    private String shiroLogin(String username,String password){
        subject = SecurityUtils.getSubject();
        logger.info("Controller用户名："+username+" Controller密码:"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(true);
        try{
            subject.login(token);
        }catch (UnknownAccountException ex){
            return "用户名或者密码错误!";
        }catch (IncorrectCredentialsException e){
            return "用户名或者密码错误!";
        }catch (Exception e){
            return "服务器忙，请重试!";
        }
        return "SUCCESS";
    }

    @RequestMapping("/success")
    public String handleSuccess(@RequestParam("username") String username,@RequestParam("password")String password){
        Session session = SecurityUtils.getSubject().getSession();
        String info = shiroLogin(username,password);
        if (!"SUCCESS".equals(info)){
            session.setAttribute("fM",info);
            return "unauthorized";
        }
        session.setAttribute("sM","登录成功");
        return "loginSuccess";
    }

    /**
     * 在controller中添加excel的获取的方法
     *  @param request
     * @param response
     * @return
     */
    @RequestMapping("/excel")
    @ResponseBody
    public String testExcel(HttpServletRequest request, HttpServletResponse response) {
        //        获取数据
        List<User> users = userService.getUsers();
        System.out.println("-------user-------"+users.toString());
//        excel 标题
        String[] titles = {"id","username","password","interest","phone","email","age","mark","sexly"};
        SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = smp.format(new Date());
//        excel的名称
        String fileName = "user_info-"+format+".xls";
//        sheet 的名字
        String sheetName = "user_info";
        int size = users.size();
        String[][] content = new String[size][];
        for (int i = 0; i < size; i ++) {
            content[i] = new String[titles.length];
            User user = users.get(i);
            content[i][0] = user.getId().toString();
            content[i][1] = user.getUsername();
            content[i][2] = user.getPassword();
            content[i][3] = user.getInterest();
            content[i][4] = user.getPhone();
            content[i][5] = user.getEmail();
            content[i][6] = user.getAge().toString();
            content[i][7] = user.getMark();
            content[i][8] = user.getSexly();
        }
        System.out.println("-------content------"+content.toString());
        HSSFWorkbook excel = ExcelUtil.getExcel(sheetName, titles, content, null);
//      相应到客户端
        this.setResponseHeader(response, fileName);
        try {
            OutputStream os = response.getOutputStream();
            excel.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Create user_info success";
    }
    //发送相应的流
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }

    @RequestMapping("/info")
    @ResponseBody
    public String userInfo(){
        PageHelper.startPage(1, 5);
        List<User> list = userService.getUsers();
        /*控制台查看数据*/
        for(User user :list){
            System.out.println(user.toString());
        }
        return list.toString();
    }

    @RequestMapping("/error")
    public String testError(){
        return "unauthorized";
    }

    @RequestMapping("/loginOut")
    public String loginOut(){

         subject = SecurityUtils.getSubject();

        if (subject != null){
            System.out.println("已经登录!");
            subject.logout();
        }
        logger.info("退出");
        return "redirect:/login";
    }

    @RequestMapping("/oneUser")
    @ResponseBody
    public String fingUserByName() throws ServiceException {
        String name = "科比";
        User user = userService.findUserByName(name);
        return JSONObject.toJSONString(user);
    }

    @RequestMapping("/pageInfo")
    @ResponseBody
    public String testPageInfo() throws ServiceException {
        Department department = new Department();
        department.setId(2);
        PageInfo<User> pageInfo = userService.getUsers(1, 3, department);
        System.out.println("-------Total---------"+pageInfo.getTotal());
        List<User> list = pageInfo.getList();
        System.out.println("----endRow---------"+pageInfo.getEndRow());
        System.out.println("-------pages----"+pageInfo.getPages());
        for (User user : list) {
            System.out.println(user.toString());
        }
        return list.toString();
    }
    @RequestMapping("/deleteInfo")
    @ResponseBody
    public String deleteInfo() throws ServiceException {
        User user = new User();
        user.setInterest("100,102,17");
       /* List<Integer> list = new ArrayList<>();
        list.add(101);
        list.add(108);
        list.add(106);
        user.setLists(list);*/
        System.out.println(user.toString());
        userService.deleteIds(user);
        return "删除成功!";
    }

    @RequestMapping("/good")
    @ResponseBody
    public String google() {
        System.out.println("只是做一个测试使用的!");
        System.out.println("只是做一个测试使用的2");
        return null;
    }

    @RequestMapping("/getFor")
    @ResponseBody
    public String getFore() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(5L);
        List<String> b = userService.getByFor(ids, "b");
        return b.toString();
    }
    @RequestMapping("/usersInsert")
    @ResponseBody
    public String usersInsert() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setPhone("1598746");
        user1.setAge(5);
        user1.setEmail("gjaoj@qq.com");
        user1.setUsername("张扬");
        user1.setPassword("123546");
        user1.setDeptId(3);
        User user2 = new User();
        user2.setPhone("974654");
        user2.setAge(56);
        user2.setEmail("mgij@qq.com");
        user2.setUsername("刘杨");
        user2.setPassword("96352");
        user2.setDeptId(6);
        User user3 = new User();
        user3.setPhone("1685452");
        user3.setAge(15);
        user3.setEmail("fjodijo@hu");
        user3.setUsername("柳杨");
        user3.setPassword("485465");
        user3.setDeptId(8);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        userService.insertInto(users);
        return "成功!";
    }
    @RequestMapping("/getPage")
    public String getPage() {
        PageBean pageBean = new PageBean();
        pageBean.setPageNum(2);
        pageBean.setPageSize(5);
        PageInfo<User> userList = userService.getUserList(pageBean);
        long total = userList.getTotal();
        System.out.println(total);
        return userList.getList().toString();
    }



}
