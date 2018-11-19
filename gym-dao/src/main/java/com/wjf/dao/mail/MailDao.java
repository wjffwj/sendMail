package com.wjf.dao.mail;

import com.wjf.domain.entity.Mail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailDao {

    public int insert(Mail mail);

    public List<Mail> getMails();

}
