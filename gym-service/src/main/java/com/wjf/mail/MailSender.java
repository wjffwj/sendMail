package com.wjf.mail;

import com.wjf.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("mailSender")
public class MailSender extends BaseService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void simpleSend(String subject,String content,String... tos){
        if (!StringUtils.isEmpty(content)) {
            SimpleMailMessage smm = new SimpleMailMessage();

            String password = javaMailSender.getPassword();

            if (!StringUtils.isEmpty(password)) {
                javaMailSender.setPassword(password);
            }
            String username = javaMailSender.getUsername();
            smm.setFrom(username);
            smm.setTo(tos);
            smm.setCc("wjffwj123@163.com");
            if (StringUtils.isEmpty(subject)) {
                smm.setSubject("每天一条...");
            } else {
                smm.setSubject(subject);
            }
            smm.setText(content);
            try {
                javaMailSender.send(smm);
                logger.info("邮件发送成功....{}",smm.getText());
            } catch (MailException e) {
                logger.error("邮件发送错误 = {}", e);
            }
        }
    }
}
