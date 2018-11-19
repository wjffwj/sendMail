package com.wjf.job;

import com.wjf.service.BaseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/spider")
public class JsoupController extends BaseService {
    @RequestMapping(value = "/spider")
    public String getMessage(ModelMap map){
        map.put("message","message");
        return "/show";
    }
}
