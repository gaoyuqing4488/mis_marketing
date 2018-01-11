package com.misrobot.mismarketing.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.misrobot.mismarketing.constants.Constants;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by GYQ on 2017/7/26.
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 功能描述：对象转成json串
     * 输入参数：<按照参数定义顺序>
     *
     * @param 参数说明 object 需要转换的实体对象
     *             返回值:  类型 <说明>
     * @return 返回值  json串
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static String getJSONString(Object object) {
        String res = null;
        try {
            res = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 功能描述：对象转成json串  排除因实体类没有某一属性 抛出UNKNOWNPROPERTIES 异常
     * 输入参数：<按照参数定义顺序>
     *
     * @param 参数说明 object 需要转换的实体对象
     *             返回值:  类型 <说明>
     * @return 返回值  json串
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static String getJSONStringExceptUNKNOWNPROPERTIES(Object object) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String res = null;
        try {
            res = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 功能描述：json串转换成指定对象
     * 输入参数：<按照参数定义顺序>
     *
     * @param jsonString 指定json串  ;clazz 需要转换后的对象class
     *                   返回值:
     * @return 返回实体对象
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static <T> T getDTO(String jsonString, Class<T> clazz) {
        T readValue = null;
        try {
            readValue = mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return readValue;
    }

    /**
     * json字符串转换成JsonNode
     * 功能描述：
     * 输入参数：<按照参数定义顺序>
     *
     * @param 参数说明 返回值:  类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static JsonNode getJsonNode(String jsonString) {
        JsonNode readTree = null;
        try {
            //TODO: Scale BUG...
            readTree = mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return readTree;
    }

    public static JsonNode getJsonNode(Object object) {
        String json = getJSONString(object);
        return getJsonNode(json);
    }

    public static JsonNode append(JsonNode jsnode) {
        ObjectNode createObjectNode = null;
        try {
            createObjectNode = mapper.createObjectNode();
            createObjectNode.put(Constants.FIRST_JSON, jsnode);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return createObjectNode;
    }

    public static String getMoney(String src) {
        BigDecimal b = new BigDecimal(src);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String getMoney(BigDecimal src) {
        return src.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

//    public static BigDecimal getMoney(String src) {
//        BigDecimal b = new BigDecimal(src);
//        return b.setScale(2, BigDecimal.ROUND_HALF_UP);
//    }

}
