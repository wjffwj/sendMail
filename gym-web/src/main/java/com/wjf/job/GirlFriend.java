package com.wjf.job;

import com.alibaba.fastjson.JSON;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import com.wjf.common.http.HttpClient;
import com.wjf.common.utils.UserUtils;
import com.wjf.domain.bo.ContentBO;
import com.wjf.domain.bo.MailBO;
import com.wjf.mail.MailSender;
import com.wjf.mananger.MailManager;
import com.wjf.service.BaseService;
import com.wjf.service.ContentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GirlFriend extends BaseService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private ContentService contentService;
    @Autowired
    private MailManager mailManager;

    @Scheduled(cron = "0 52 0,8,10,12,14,16,18,20,22 * * ?")
    public void executeMail(){
        this.info("任务开始执行....."+new Date());

        ContentBO contentBO = contentService.selectByRandom();
        this.info("查询出来的content数据是"+ JSON.toJSONString(contentBO));
        String content=null;
        if(!Objects.isNull(contentBO)){
            content = contentBO.getContent();
            String sender = "\n\n\n\n                          -------------------老魏";
            content=content+sender;
            mailSender.simpleSend("I am missing you~~~ ",content,UserUtils.SEND_MAIL);
            mailSender.simpleSend("I am missing you~~~ ",content,UserUtils.ME);
        }
        try {
            MailBO mailBO=new MailBO();
            mailBO.setContent(content);
            mailBO.setCreated(new Date());
            mailBO.setStatus(1);
            mailBO.setUpdated(new Date());
            mailManager.insert(mailBO);
        }catch (Exception e){
            this.info("记录存库失败...");
            e.printStackTrace();
        }
        this.info("发送邮件任务结束.....");

    }
    @Scheduled(cron = "0 20 8 * * ?")
    public void executeTianQi(){
        try {
            Document document = Jsoup.connect("http://www.tianqi.com/fularjiqu/").get();
            Elements elements = document.select("dd[class=name]");
            Element element = elements.get(0);
            Elements p = element.select("h2");
            //城市名称
            String cityName = p.text();
            Element date = document.select("dd[class=week]").get(0);
            //日期
            String dateTime = date.text();
            //当前温度
            String wenDu = document.select("p[class=now]").get(0).text();
            //温度区间
            String wenDuQujian = document.select("dd[class=weather]").select("span").get(0).text();
            // System.out.println(wenDuQujian);
            //空气质量
            String kQ= document.select("dd[class=kongqi]").get(0).text();
            //  System.out.println(kQ);
            String detail=cityName+"\n"+"日期："+dateTime+"\n"+"当前温度："+wenDu+"\n"+"今日温度区间："+wenDuQujian+"\n"+
                    kQ+"\n"+"爱你哦~";
            System.out.println(detail);
           // mailSender.simpleSend("今日天气情况 ",detail,UserUtils.SEND_MAIL);
            mailSender.simpleSend("今日天气情况 ",detail,UserUtils.ME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
