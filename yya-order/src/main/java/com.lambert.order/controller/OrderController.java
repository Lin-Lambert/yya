package com.lambert.order.controller;

import com.lambert.order.bean.TtguiguResult;
import com.lambert.order.pojo.Order;
import com.lambert.order.pojo.PageResult;
import com.lambert.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lambert.order.pojo.ResultMsg;

@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 创建订单
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public TtguiguResult createOrder(@RequestBody String json) {
		return orderService.createOrder(json);
	}
	
	
	/**
	 * 根据订单ID查询订单
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/query/{orderId}" ,method = RequestMethod.GET)
	public Order queryOrderById(@PathVariable("orderId") String orderId) {
		return orderService.queryOrderById(orderId);
	}

	/**
	 * 根据用户名分页查询订单
	 * @param userId
	 * @param page
	 * @param count
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query/{userId}/{page}/{count}")
	public PageResult<Order> queryOrderByUserNameAndPage(@PathVariable("userId") Long userId, @PathVariable("page") Integer page, @PathVariable("count") Integer count) {
		return orderService.queryOrderByUserNameAndPage(userId, page, count);
	}

	
	/**
	 * 修改订单状态
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/changeOrderStatus",method = RequestMethod.POST)
	public ResultMsg changeOrderStatus(@RequestBody String json) {
		return orderService.changeOrderStatus(json);
	}
}
