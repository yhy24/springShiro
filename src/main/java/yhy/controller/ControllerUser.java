package yhy.controller;

import org.apache.log4j.Logger;
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
import yhy.pojo.User;
import yhy.service.UserService;
import javax.annotation.Resource;
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
        return user.toString();
    }
/*没有实际的意义只是测试代码*/
    @RequestMapping("/oneUser")
    @ResponseBody
    public String fingUserById() throws ServiceException {
        String id = "24";
        User user = userService.findUserByName(id);
        return user.toString();
    }

}
