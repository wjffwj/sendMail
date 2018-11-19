package com.wjf.inner.service;

import com.wjf.inner.domain.MailDTO;

import java.util.List;

public interface MailInnerFacade {
    public List<MailDTO> getSendedMail();
}
