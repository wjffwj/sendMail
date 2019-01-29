package com.wjf.sb;

import com.sun.media.jfxmedia.logging.Logger;
import com.wjf.test.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/export")
public class CsvController {
    Student getStudent(){
        Student student=new Student();
        student.setId(1);
        student.setName("魏俊峰");
        return student;
    }

    @RequestMapping(value = "/sb")
    public String getCsv(HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> dataList = new ArrayList<>();
        List<Student> list=new ArrayList<>();                   Student student=getStudent();list.add(student);
        String sTitle="编号,学生姓名";
        String fileName="sbCsv.csv";
        String mapKey="id,name";
        for(Student s:list){
            Map<String,Object> map=new HashMap<>();
            map.put("id",String.valueOf(s.getId()));
            map.put("name",s.getName());
            dataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fileName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);

        }catch (Exception e){
            System.out.println("生气.....");
        }

        return "/show";
    }
}
