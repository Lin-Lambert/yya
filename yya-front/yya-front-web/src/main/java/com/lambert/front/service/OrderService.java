package com.lambert.front.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.cart.bean.HttpResult;
import com.lambert.cart.service.HttpClientUtils;
import com.lambert.front.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private HttpClientUtils httpClientUtils;

    @Value("${ATGUIGU_ORDER_URL}")
    private String ATGUIGU_ORDER_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public String createOrder(Order order) {

        try {
            String url = ATGUIGU_ORDER_URL + "/order/create";

            String writeValueAsString = MAPPER.writeValueAsString(order);

            HttpResult httpResult = this.httpClientUtils.doPostJson(url, writeValueAsString);

            if (httpResult.getCode() == 200) {
                //响应成功
                String jsonData = httpResult.getBody();
                JsonNode jsonNode = MAPPER.readTree(jsonData);

                if (jsonNode.get("status").intValue() == 200) {
                    String data = jsonNode.get("data").asText();
                    return data;
                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
