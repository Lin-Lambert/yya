package com.lambert.product.service.mq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.product.service.utils.RedisUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMQ {

    @Autowired
    private RedisUtilsImpl redisUtilsImpl;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //自已业务处理 删除redis
    public void excuete(String msg) {
        //删除redis缓存
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            String productId = jsonNode.get("productId").asText();

            this.redisUtilsImpl.del("ATGUIGU_PRODUCT_WEB_"+productId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
