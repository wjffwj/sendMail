package com.wjf.job;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.wjf.domain.bo.ContentBO;
import com.wjf.service.BaseService;
import com.wjf.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@SuppressWarnings("all")
public class SmsWorker extends BaseService {
    private final Integer appId=1400132788;
    private final String appKey="8083e0aa2c18b7e2d0c916630ec9b38b";
    private final String[] phoneNumbers={"18845635572"};
    private final Integer templateId= 183762;//短信正文模板Id
    private final String signName="猪皮恶霸的技术博客";
    private final String[] params={"183742"};
    @Autowired
    private ContentService contentService;
    @Scheduled(cron = "0 52 22 * * ?")
    public void sendSms(){
        this.info("短信-任务开始执行....."+new Date());
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
           try {
               SmsSingleSenderResult result = ssender.send(0,"86","15901254195","我爱你\n"+"为您的登录验证码","","");
               this.info(result.errMsg);
           }catch (Exception e){
               this.info("发送短信失败");
           }
    }

}
