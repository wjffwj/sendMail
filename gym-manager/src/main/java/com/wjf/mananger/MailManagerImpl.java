package com.wjf.mananger;

import com.google.common.collect.Lists;
import com.wjf.common.utils.BeanMapper;
import com.wjf.dao.mail.MailDao;
import com.wjf.domain.bo.MailBO;
import com.wjf.domain.entity.Mail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MailManagerImpl implements MailManager {
    @Resource
    private MailDao mailDao;
    public int insert(MailBO mail) {
        Mail mail1=new Mail();
        BeanUtils.copyProperties(mail,mail1);

        return mailDao.insert(mail1);
    }

    public List<MailBO> getMails() {
        List<MailBO> mailBOS= Lists.newArrayList();
        List<Mail> mails = mailDao.getMails();
        BeanMapper.copy(mails,mailBOS);
        return mailBOS;
    }
}
