package com.wjf.dao.content;

import com.wjf.domain.entity.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDao {

    public int insert(Content content);//添加qh

    public Content selectById(Integer id);//查询qh

    public Content selectByRandom();//随机搜索一条记录


}
