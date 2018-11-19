import com.wjf.domain.bo.ContentBO;
import com.wjf.domain.entity.Content;
import com.wjf.job.GirlFriend;
import com.wjf.mananger.ContentManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Scanner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"/*,"classpath:spring-web.xml"*/})
/**
 * 爬虫
 */
public class Test {
    @Autowired
    private ContentManager contentManager;
    @Autowired
    private GirlFriend girlFriend;

    @org.junit.Test
    public void test() {
        try {
            Document document = Jsoup.connect("http://www.1juzi.com/new/45861.html").get();
            Element element = document.getElementById("content");
            Element ele = element.getElementById("main").getElementById("article");
            Elements select = ele.select("div[class=content]");
            Elements p = select.select("p");
            for (Element e : p) {
                ContentBO content = new ContentBO();
                content.setContent(e.text());
                content.setCreated(new Date());
                contentManager.insert(content);
            }

            System.out.println(1);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 调用发送邮件接口
     */
    @org.junit.Test
    public void test1() {
        girlFriend.executeMail();
    }

    /**
     * 爬虫抓取数据入库
     */
    @org.junit.Test
    public void saveLove() {
        try {
            Document document = Jsoup.connect("http://www.lz13.cn/jingdianyuduan/53496.html").get();
            Elements elements = document.select("p");
            elements.stream().forEach(ele -> {
                ContentBO contentBO = new ContentBO();
                if (ele.text() != null) {
                    contentBO.setCreated(new Date());
                    contentBO.setContent(ele.text());
                    contentManager.insert(contentBO);
                }
            });
            System.out.println(1);
        } catch (Exception e) {
            System.out.println("出错啦...");
            e.printStackTrace();
        }
    }

    /**
     * 爬虫抓取数据落库
     */
    @org.junit.Test
    public void saveLove1() {
//        try{
//            Document document = Jsoup.connect("").get();
//        }catch (Exception e){
//            System.out.println("出错来...");
//            e.printStackTrace();
//        }
    }


    @org.junit.Test
    public void saveLove2() {
        String input = new Scanner(System.in).next();
        if (input.equals("晚安")){
            System.out.println("想你");
        }
    }

    @org.junit.Test
    public void testDate(){
        Date date=new Date();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date1=new Date();
        if(date1.after(date)){
            System.out.println("ok");
        }else {
            System.out.println("操蛋。。。");
        }
    }
    @org.junit.Test
    public void testStringToLong(){
        String str=new String("87123123123");
        Long value=Long.valueOf(str);
        System.out.println(value);
    }
    @org.junit.Test
    public void testExit(){
        Boolean exist=null;
        exist=false;
        if(exist!=null&&!exist){
            System.out.println("ok");
        }else{
            System.out.println(1);
        }
    }
    @org.junit.Test
    public void testOrg(){
        Boolean f=false;
        System.out.println(f.toString());

    }

}
