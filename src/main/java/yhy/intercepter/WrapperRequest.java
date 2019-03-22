package yhy.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yhy
 * @Date: 2019/3/14 16:49
 * @Version 1.0
 * 包装类
 */
public class WrapperRequest extends HttpServletRequestWrapper {

    private Map<String, String[]> parameters = new HashMap<>();

    public WrapperRequest(HttpServletRequest request) {
        super(request);
        this.parameters.putAll(request.getParameterMap());
    }

    public void setParater(String name,String[] str) {
        String[] strings = this.parameters.get(name);
        if (strings.length > 0) {
            this.parameters.put(name, str);
        }
    }


    /**
     * 重写getParameter，代表参数从当前类中的parameters获取
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        return parameters.get(name)[0];
    }
    /**
     * 重写getParameterValues方法，从当前类的 map中取值
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameters.get(name);
    }
}
