package yhy.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: yhy
 * @Date: 2018/9/14 9:12
 * @Version 1.0
 * 把参数封装在一个BO中当做条件使用
 */
public class DeleteConditionBO implements Serializable {

    private static final long serialVersionUID = 7852560868693265442L;
    private String code;
    private List<Integer> ids;

    public DeleteConditionBO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
