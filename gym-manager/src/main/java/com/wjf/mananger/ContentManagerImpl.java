package com.wjf.mananger;

import com.wjf.dao.content.ContentDao;
import com.wjf.domain.bo.ContentBO;
import com.wjf.domain.entity.Content;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentManagerImpl implements ContentManager {
    @Autowired
    private ContentDao contentDao;
    public int insert(ContentBO content) {
        Content content1=new Content();
        BeanUtils.copyProperties(content,content1);
        return contentDao.insert(content1);
    }

    public ContentBO selectById(Integer id) {
        Content content = contentDao.selectById(id);
        ContentBO bo=new ContentBO();
        BeanUtils.copyProperties(content,bo);
        return bo;
    }

    public ContentBO selectByRandom() {
        Content content = contentDao.selectByRandom();
        ContentBO contentBO=new ContentBO();
        BeanUtils.copyProperties(content,contentBO);
        return contentBO;
    }
}
