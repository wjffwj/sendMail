package com.wjf.service;

import com.wjf.domain.bo.ContentBO;

public interface ContentService {
    public int insert(ContentBO content);//添加qh

    public ContentBO selectById(Integer id);//查询qh

    public ContentBO selectByRandom();//随机搜索一条记录
}
