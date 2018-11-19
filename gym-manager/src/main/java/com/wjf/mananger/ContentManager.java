package com.wjf.mananger;

import com.wjf.domain.bo.ContentBO;
import com.wjf.domain.entity.Content;

public interface ContentManager {
    public int insert(ContentBO content);//添加qh

    public ContentBO selectById(Integer id);//查询qh

    public ContentBO selectByRandom();//随机搜索一条记录
}
