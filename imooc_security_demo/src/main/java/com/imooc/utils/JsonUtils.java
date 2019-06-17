package com.imooc.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.imooc.pojo.Aa;

import java.util.List;

/**
 * @Author: cmy
 * @CreateTime: 2018-09-20 11:08
 * @Description:
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //忽略在Json字符串中存在，但是在java对象中不存在对应属性的情况
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }



    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }

    /**
     * 将对象转换成json字符串(美化输出)。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJsonWithPretty(Object data) {
        try {
            String string = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }


    public static void main(String[] args) {
        String a="{\n" +
                "\t\"twitterId\": \"\",\n" +
                "\t\"amount\": \"0.30\",\n" +
                "\t\"buyWay\": 1,\n" +
                "\t\"cartList\": [{\n" +
                "\t\t\"customerOpenid\": \"oTpr-0EWcQazOzRPwf-7yW2EM1AM\",\n" +
                "\t\t\"itemAmount\": 0.1,\n" +
                "\t\t\"page\": 1,\n" +
                "\t\t\"productId\": \"1042587596837879808\",\n" +
                "\t\t\"id\": \"1066857465112428544\",\n" +
                "\t\t\"productSnapshot\": \"{\\\"productId\\\":1042587596837879808,\\\"productMainImage\\\":\\\"M00/1C/E8/ZSUkmVui-OWEMehTAAAAAHnXgq8988.jpg\\\",\\\"productName\\\":\\\"商品2（有分享语）\\\",\\\"skuId\\\":1042588555244732416,\\\"skuName\\\":\\\"M码|黄色\\\",\\\"skuPrice\\\":0.01}\",\n" +
                "\t\t\"quantity\": 10,\n" +
                "\t\t\"size\": 10,\n" +
                "\t\t\"skuId\": \"1042588555244732416\",\n" +
                "\t\t\"status\": 0\n" +
                "\t}, {\n" +
                "\t\t\"customerOpenid\": \"oTpr-0EWcQazOzRPwf-7yW2EM1AM\",\n" +
                "\t\t\"itemAmount\": 0.2,\n" +
                "\t\t\"page\": 1,\n" +
                "\t\t\"productId\": \"1042589060620615680\",\n" +
                "\t\t\"id\": \"1066857504480165888\",\n" +
                "\t\t\"productSnapshot\": \"{\\\"productId\\\":1042589060620615680,\\\"productMainImage\\\":\\\"M00/1C/E8/ZSUkmVui-kCEZ-6tAAAAAHnXgq8244.jpg\\\",\\\"productName\\\":\\\"商品3（有虚拟销量6666）\\\",\\\"skuId\\\":1042591928241094656,\\\"skuName\\\":\\\"A码|红色|www\\\",\\\"skuPrice\\\":0.01}\",\n" +
                "\t\t\"quantity\": 20,\n" +
                "\t\t\"size\": 10,\n" +
                "\t\t\"skuId\": \"1042591928241094656\",\n" +
                "\t\t\"status\": 0\n" +
                "\t}],\n" +
                "\t\"discountAmount\": 0,\n" +
                "\t\"discountId\": \"\",\n" +
                "\t\"discountType\": \"\",\n" +
                "\t\"redpacketLogId\": \"\",\n" +
                "\t\"freightAmount\": 0.02,\n" +
                "\t\"page\": 1,\n" +
                "\t\"score\": 20,\n" +
                "\t\"scoreAmount\": 0.02,\n" +
                "\t\"size\": 1,\n" +
                "\t\"token\": \"3349aa7b-74db-48d1-9c82-1d79a3434486\",\n" +
                "\t\"province\": \"浙江省\",\n" +
                "\t\"city\": \"宁波市\",\n" +
                "\t\"address\": \"浙江省宁波市鄞州区汇港大厦(宁波市鄞州区泰安中路466号)\",\n" +
                "\t\"consignee\": \"蔡明洋\",\n" +
                "\t\"phone\": \"13024605668\",\n" +
                "\t\"remark\": \"\"\n" +
                "}";
        Aa aa = JsonUtils.jsonToPojo(a, Aa.class);
        System.out.println(aa);
    }
}
