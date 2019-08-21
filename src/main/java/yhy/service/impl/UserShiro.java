package yhy.service.impl;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import yhy.exception.ServiceException;
import yhy.pojo.User;
import yhy.service.UserService;

import javax.annotation.Resource;
import java.util.Set;

public class UserShiro extends AuthorizingRealm implements Realm{
    private static Logger logger = Logger.getLogger(UserShiro.class);

    @Resource
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = null;
        try {
            User user = loninUser(name);
//            Set<Stirng> roles = user.getRoles();

        } catch (ServiceException e) {
            e.printStackTrace();
        }
//        权限认证
        return new SimpleAuthorizationInfo(roles);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
//登录认证
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String name= upToken.getUsername();
        User user = null;

        try {
            user = loninUser(name);
        } catch (ServiceException e) {}
        String pwd = new String(upToken.getPassword());
        System.out.println("输入的用户名："+name+" 输入的密码:"+pwd+" 数据库的密码:"+user.getPassword());

        if (!pwd.equals(user.getPassword())){
            return null;
        }
        Object principals = name;
        String realName = getName();
        ByteSource salt = ByteSource.Util.bytes(name);
        Object credentials = upToken.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals,credentials,realName);
        // MD5加密 使用这个 SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals,credentials,salt,realName);
        return info;
    }

    private User loninUser(String name) throws ServiceException {
        /*从数据库中获取用户的信息*/
        User user= userService.findUserByName(name);
        if(user == null){
            throw new ServiceException("9999","该用户不存在的!");
        }
        return user;
    }


    public static void main(String[] args) {
        /*测试获取加密后的密码  4a61d6f93e88b44fce4f0993b0a4acd5*/
        String prc="MD5";
        Object source = ByteSource.Util.bytes("杨浩营");//盐值为自己的名字
        String pwd = "123";
        int times = 1024;
        Object MD5 = new SimpleHash(prc,pwd,source,times);
        logger.info("--->"+MD5);
    }




}
