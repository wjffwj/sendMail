package com.wjf.utils;

import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: JSON自封装工具类
 * @author: libin293
 * @createdate: 2017-09-28 10:02
 * @lastdate: 2017/9/28
 */
public class JsonUtils {

    private static JsonMapper mapper = JsonMapper.buildNormalMapper();


    /**
     * 1.指定Class信息反序列化
     *
     * @return json串 如： {"rows":[{},{}],"totalCount":10}
     */
    public static <T> T json2Vo(String json, Class<T> clz) {
        T obj = mapper.fromJson(json, clz);
        return obj;
    }


    public static <T> T json2VoGenerics(String json, Class<T> clz) {
        return mapper.fromJson(json, clz);
    }

    /**
     * 2.类型集合的反序列化
     */
    public static <T> List<T> json2VoList(String json, Class<T> clz) {
        JavaType javaType = mapper.constructParametricType(ArrayList.class, clz);
        return mapper.fromJson(json, javaType);
    }


    /**
     * 3.类型集合的反序列化
     */
    public static String D(List<Object> list) {
        String json = mapper.toJson(list);
        return json;
    }


    public static <T> T readValue(String josnStr, TypeReference<T> t) throws RuntimeException {
        try {
            return mapper.readValue(josnStr, t);
        } catch (RuntimeException var3) {
            throw new RuntimeException(var3);
        }
    }


    /**
     * 4.将实体VO Object 或者 JavaBean换成Json字符串
     *
     * @param vo
     * @return
     */
    public static String vo2JsonString(Object vo) {
        String json = mapper.toJson(vo);
        return json;
    }
}