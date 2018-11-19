package com.wjf.domain.query;

public class MailQuery {
    private int status;//状态
    private int start;//从第几条开始
    private int size;//每次查多少数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
