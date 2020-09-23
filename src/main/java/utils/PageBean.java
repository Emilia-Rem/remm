package utils;

import java.util.List;

public class PageBean<T> {
    //页码
    private int pageNum;
    //每页显示多少数据
    private int pageSize;
    //总页数
    private int pageCount;
    //总数据数
    private long totalSize;
    //当前数据
    private List<T> data;
    //起始页面和最后页面
    private int pageStart;
    private int pageEnd;

    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.data = data;
        pageCount = (int)totalSize%pageSize==0?(int)totalSize/pageSize:(int) totalSize/pageSize+1;

        //正常情况
        pageStart = pageNum-4;
        pageEnd = pageNum+4;
        //前面不够显示
        if(pageNum<5){
            pageStart = 1;
            pageEnd = 9;
        }
        //后面不够显示
        if(pageCount - pageNum<5){
            pageStart = pageCount - 8;
            pageEnd = pageCount;
        }
        //总页面就不够
        if(pageCount<9){
            pageStart = 1;
            pageEnd = pageCount;
        }
    }

    public PageBean() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
}
