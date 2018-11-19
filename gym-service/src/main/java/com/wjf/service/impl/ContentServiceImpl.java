package com.wjf.service.impl;

import com.wjf.domain.bo.ContentBO;
import com.wjf.mananger.ContentManager;
import com.wjf.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentManager contentManager;
    public int insert(ContentBO content) {
        return contentManager.insert(content);
    }

    public ContentBO selectById(Integer id) {
        ContentBO contentBO = contentManager.selectById(id);
        return contentBO;
    }

    public ContentBO selectByRandom() {
        ContentBO contentBO = contentManager.selectByRandom();
        return contentBO;
    }
}
