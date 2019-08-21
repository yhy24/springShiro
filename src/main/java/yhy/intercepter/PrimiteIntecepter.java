package yhy.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import yhy.pojo.User;
import yhy.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Author: yhy
 * @Date: 2019/3/13 10:51
 * @Version 1.0
 */
public class PrimiteIntecepter extends HandlerInterceptorAdapter {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    @Resource
    UserService userService;

    public PrimiteIntecepter() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = urlPathHelper.getLookupPathForRequest(request);
       /* Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("******"+cookie);
        }*/

      /*  HttpSession session = request.getSession(false);
        User user= (User) session.getAttribute("userInfo");
        if (user == null) {
            PrintWriter writer = response.getWriter();
            writer.write("没有登录,请先登录!");
            writer.flush();
            writer.close();
            return false;
        }*/
        System.out.println(url);
        System.out.println("preHandle.............");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
