package yhy.intercepter;

import org.apache.poi.util.IOUtils;
import org.springframework.web.util.UrlPathHelper;
import sun.nio.ch.IOUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        String method = ((HttpServletRequest) request).getMethod();
        if (!checkUrl(url)) {
            String requestBody = getRequestBody((HttpServletRequest) request);
            System.out.println(requestBody);
            chain.doFilter(request, response);
        //TODO对返回的数据进行处理或者加密使用


        } else {
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
            return true;
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
