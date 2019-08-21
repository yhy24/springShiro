package yhy.intercepter;

import org.apache.poi.util.IOUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: yhy
 * @Date: 2019/3/13 10:50
 * @Version 1.0
 */
public class DataFilter implements Filter {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("进行初始化的使用的配置.........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = urlPathHelper.getLookupPathForRequest((HttpServletRequest) request);
//        String method = ((HttpServletRequest) request).getMethod();
//        Map<String,String[]> parameterMap = ((HttpServletRequest) request).getParameterMap();
//        System.out.println(parameterMap******);
//        ((HttpServletRequest) request).getParameterMap();
//        拦截器是的使用
        if (checkUrl(url)) {
//            WrapperRequest wrapperRequest = new WrapperRequest((HttpServletRequest) request);
//            wrapper.setParater("username", new String[]{"ldp"});
//            Map<String,String[]> parameter = ((HttpServletRequest) request).getParameterMap();
//            System.out.println(parameter);
            String requestBody2 = getRequestBody((HttpServletRequest) request);
            System.out.println(requestBody2);
//            chain.doFilter(wapperRequest, response);
            chain.doFilter(request, response);
//            生如夏花之绚烂，死如秋叶之静美
        //TODO对返回的数据进行处理或者加密使用
//org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'text/plain;charset=UTF-8' not supported
        } else {
           /* String requestBody = getRequestBody((HttpServletRequest) request);
            System.out.println(requestBody);*/
//            调用拦截器
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("进行销毁的处理使用..........");
    }
/*对部分进行放行处理*/
    public boolean checkUrl(String url) {
        if (url.startsWith("/testUser")) {
            return true;
        } else if (url.startsWith("/getPage")) {
            return true;
        } else if (url.startsWith("/success")) {
            return true;
        } else if (url.startsWith("/login")) {
            return false;
        } else {
            return false;
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return new String(bytes,"utf-8");
    }
}
