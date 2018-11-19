package com.wjf.mananger;

import com.wjf.domain.bo.MailBO;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MailManager {
    public int insert(MailBO mail);

    public List<MailBO> getMails();


}
