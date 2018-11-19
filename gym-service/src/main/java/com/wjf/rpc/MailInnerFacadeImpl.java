package com.wjf.rpc;

import com.wjf.common.utils.BeanMapper;
import com.wjf.domain.bo.MailBO;
import com.wjf.inner.domain.MailDTO;
import com.wjf.inner.service.MailInnerFacade;
import com.wjf.mananger.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MailInnerFacadeImpl implements MailInnerFacade {
    @Autowired
    private MailManager mailManager;
    public List<MailDTO> getSendedMail() {
        List<MailDTO> list=new ArrayList<MailDTO>();
        List<MailBO> mails = mailManager.getMails();
        BeanMapper.copy(mails,list);
        return list;
    }
}
