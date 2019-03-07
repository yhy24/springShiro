package yhy.pojo;

import com.github.pagehelper.PageInfo;

/**
 * @Author: yhy
 * @Date: 2019/3/4 17:41
 * @Version 1.0
 */
public class PageBean {
    private int pageNum;
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
