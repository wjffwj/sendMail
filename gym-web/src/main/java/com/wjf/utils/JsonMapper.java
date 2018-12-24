/**
 * @Title: JsonMapper.java
 * @Package com.asp.mobile.util.json
 * @Description: TODO
 * @author haifeng.li
 * @date 2013-3-8 下午12:27:17
 * @version V1.0
 */
package com.wjf.utils;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.*;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @ClassName: JsonMapper
 * @Description: 简单封装Jackson实现JSON<->Java Object的Mapper.
 *               封装不同的输出风格, 使用不同的builder函数创建实例.
 * @author libin293
 * @date 2017-9-28 下午12:27:17
 *
 */
public class JsonMapper {

    private ObjectMapper mapper;

    protected Logger LogUtil = LoggerFactory.getLogger(this.getClass());

    public JsonMapper(Inclusion inclusion) {
        mapper = new ObjectMapper();
        //设置输出时包含属性的风格
        mapper.setSerializationInclusion(inclusion);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //允许字段名不带引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
//		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    }

    /**
     * 创建输出全部属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNormalMapper() {
        return new JsonMapper(Inclusion.ALWAYS);
    }

    /**
     * 创建只输出非空属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNonNullMapper() {
        return new JsonMapper(Inclusion.NON_NULL);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Binder.
     */
    public static JsonMapper buildNonDefaultBinder() {
        return new JsonMapper(Inclusion.NON_DEFAULT);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNonDefaultMapper() {
        return new JsonMapper(Inclusion.NON_DEFAULT);
    }

    /**
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时使用如下语句,使用后面的函數.
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            LogUtil.error("parse json string error:" + e);
            return null;
        }
    }

    public <T> T readValue(String josnStr, TypeReference<T> t) throws RuntimeException {
        try {
            return mapper.readValue(josnStr, t);
        } catch (JsonParseException var3) {
            LogUtil.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LogUtil.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LogUtil.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    /**
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     *
     * 如需读取集合如List/Map, 且不是List<String>時,
     * 先用constructParametricType(List.class,MyBean.class)構造出JavaTeype,再調用本函數.
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            LogUtil.error("parse json string error:" + e);
            return null;
        }
    }

    /**
     * 構造泛型的Type如List<MyBean>, Map<String,MyBean>
     */
    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    /**
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            LogUtil.error("write to json string error: " + e);
            return null;
        }
    }

    /**
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    public String toJson(Object object, String[] includeFields) {
        try {
            mapper.setSerializationConfig(new SerializationConfig(BasicClassIntrospector.instance,
                    new CustomerIntrospector(includeFields), VisibilityChecker.Std.defaultInstance(), null, null,
                    TypeFactory.defaultInstance(), null));
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            LogUtil.error("write to json string error: {} {}", this.getClass(), object, e);
            return null;
        }
    }

    /**
     * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖该部分的属性.
     */
    @SuppressWarnings("unchecked")
    public <T> T update(T object, String jsonString) {
        try {
            return (T) mapper.updatingReader(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            LogUtil.error("parse json string {} to object: {} error {}.", this.getClass(), jsonString, object, e);
        } catch (IOException e) {
            LogUtil.error("parse json string {} to object:{} error {}.", this.getClass(), jsonString, object, e);
        }
        return null;
    }

    /**
     * 输出JSONP格式数据.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void setEnumUseToString(boolean value) {
        mapper.getSerializationConfig().set(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
        mapper.getDeserializationConfig().set(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    public class CustomerIntrospector extends JacksonAnnotationIntrospector {

        private Set<String> includefileds;

        public CustomerIntrospector(String[] f) {
            includefileds = new HashSet<String>();
            for (String field : f) {
                includefileds.add(field);
            }
        }

        @Override
        public String[] findPropertiesToIgnore(AnnotatedClass ac) {
            if (ac == null) {
                return null;
            }

            List<String> list = new ArrayList<String>();

            Iterable<AnnotatedField> fields = ac.fields();
            for (AnnotatedField field : fields) {
                if (!includefileds.contains(field.getName())) {
                    list.add(field.getName());
                }
            }
            return list.toArray(new String[]{});
        }
    }
}
