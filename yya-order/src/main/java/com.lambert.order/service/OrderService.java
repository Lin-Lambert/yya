package com.lambert.order.service;

import java.util.Date;

import com.lambert.order.bean.TtguiguResult;
import com.lambert.order.dao.IOrder;
import com.lambert.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambert.order.pojo.PageResult;
import com.lambert.order.pojo.ResultMsg;
import com.lambert.order.util.ValidateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IOrder orderDao;
    
//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    public TtguiguResult createOrder(String json) {
        Order order = null;
        try {
            order = objectMapper.readValue(json, Order.class);
            // 校验Order对象
            ValidateUtil.validate(order);
        } catch (Exception e) {
            return TtguiguResult.build(400, "请求参数有误!");
        }

        try {
            // 生成订单ID，规则为：userid+当前时间戳
            String orderId = order.getUserId() + "" + System.currentTimeMillis();
            order.setOrderId(orderId);

            // 设置订单的初始状态为未付款
            order.setStatus(1);

            // 设置订单的创建时间
            order.setCreateTime(new Date());
            order.setUpdateTime(order.getCreateTime());

            // 设置买家评价状态，初始为未评价
            order.setBuyerRate(0);

            // 持久化order对象
            orderDao.createOrder(order);
            
            //发送消息到RabbitMQ
//            Map<String, Object> msg = new HashMap<String, Object>(3);
//            msg.put("userId", order.getUserId());
//            msg.put("orderId", order.getOrderId());
//            List<Long> productIds = new ArrayList<Long>(order.getOrderProducts().size());
//            for (OrderProduct orderProduct : order.getOrderProducts()) {
//                productIds.add(orderProduct.getProductId());
//            }
//            msg.put("productIds", productIds);
//            this.rabbitTemplate.convertAndSend(objectMapper.writeValueAsString(msg));
            
            return TtguiguResult.ok(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TtguiguResult.build(400, "保存订单失败!");
    }

    public Order queryOrderById(String orderId) {
        Order order = orderDao.queryOrderById(orderId);
        return order;
    }

    public PageResult<Order> queryOrderByUserNameAndPage(Long userId, int page, int count) {
        return orderDao.queryOrderByUserNameAndPage(userId, page, count);
    }

    public ResultMsg changeOrderStatus(String json) {
        Order order = null;
        try {
            order = objectMapper.readValue(json, Order.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg("400", "请求参数有误!");
        }
        return this.orderDao.changeOrderStatus(order);
    }

}
