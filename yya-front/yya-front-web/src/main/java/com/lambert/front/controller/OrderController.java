package com.lambert.front.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lambert.cart.service.CartService;
import com.lambert.cart.service.HttpClientUtils;
import com.lambert.common.utils.CookieUtils;
import com.lambert.front.bean.Order;
import com.lambert.front.bean.PageResult;
import com.lambert.front.service.OrderService;
import com.lambert.manager.pojo.Cart;
import com.lambert.manager.pojo.Product;
import com.lambert.manager.pojo.User;
import com.lambert.manager.pojo.UserShipping;
import com.lambert.sso.service.UserService;
import com.lambert.sso.service.UserShippingService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Value("${ATGUIGU_MANAGER_URL}")
    private String ATGUIGU_MANAGER_URL;

    @Value("${ATGUIGU_ORDER_URL}")
    private String ATGUIGU_ORDER_URL;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private CartService cartService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RequestMapping("orderinfo")
    public ModelAndView toOrderInfo(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("orderinfo");
        try {
            String ticket = CookieUtils.getCookieValue(request, "YOUYIJIA_TICKET", true);
            if (!"".equals(ticket)) {
                User user = userService.queryByTicket(ticket);
                String url = ATGUIGU_ORDER_URL + "/order/query/" + user.getId() +"/1/1000";
                String jsonData = httpClientUtils.doget(url);
                if(jsonData != null){
                    PageResult pageResult = MAPPER.readValue(jsonData, PageResult.class);
                    mv.addObject("pageResult", pageResult);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * 显示下单页
     */
    @RequestMapping(value = "/toOrder", method = RequestMethod.GET)
    public ModelAndView toOrder(@RequestParam("pids") String pids, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("order");
        try {
            String ticket = CookieUtils.getCookieValue(request, "YOUYIJIA_TICKET", true);
            if (!"".equals(ticket)) {
                User user = userService.queryByTicket(ticket);
                List<UserShipping> shippings = userShippingService.queryUserShippingByUserId(user.getId());
                List<Cart> cartList = cartService.queryProductByIds(pids);
                if (cartList != null) {
                    mv.addObject("products", cartList);
                    mv.addObject("shippings", shippings);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 下单
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doOrder(Order order, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        //设置用户
        String ticket = CookieUtils.getCookieValue(request, "YOUYIJIA_TICKET", true);

        //根据ticket查询用户信息
        if (!"".equals(ticket)) {
            try {
                User user = userService.queryByTicket(ticket);
                if (user != null) {
                    order.setUserId(user.getId());
                    order.setBuyerNick(user.getUsername());
                    String orderId = this.orderService.createOrder(order);

                    if (StringUtils.isNoneBlank(orderId)) {
                        //创建订单成功
                        result.put("status", "200");
                        result.put("data", orderId);
                    } else {
                        result.put("status", "500");
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 显示下单成功页
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public ModelAndView success(@RequestParam("id") String orderId) {
        ModelAndView mv = new ModelAndView("success");
        try {
            String url = ATGUIGU_ORDER_URL + "/order/query/" + orderId;
            String jsonData = this.httpClientUtils.doget(url);
            if (jsonData != null) {
                Order order = MAPPER.readValue(jsonData, Order.class);
                mv.addObject("order", order);
                mv.addObject("date", new DateTime().plusDays(2).toString("MM月dd日"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/toAddShipping")
    public ModelAndView toAddShipping(@RequestParam("pids") String pids) {
        ModelAndView mv = new ModelAndView("addShipping");
        mv.addObject("pids", pids);
        return mv;
    }

    @RequestMapping(value = "/toEditShipping")
    public ModelAndView toEditShipping(@RequestParam("pids") String pids, @RequestParam("userShippingId") Long userShippingId) {
        ModelAndView mv = new ModelAndView("editShipping");
        mv.addObject("pids", pids);
        UserShipping userShipping = userShippingService.queryUserShippingById(userShippingId);
        mv.addObject("userShipping", userShipping);
        return mv;
    }

    @RequestMapping(value = "/doAddShipping")
    public ModelAndView doAddShipping(@RequestParam("pids") String pids, UserShipping userShipping, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/order/toOrder.html");
        try {
            String ticket = CookieUtils.getCookieValue(request, "YOUYIJIA_TICKET", true);
            if (!"".equals(ticket)) {
                User user = userService.queryByTicket(ticket);
                userShipping.setUserId(user.getId());
                userShippingService.saveUserShipping(userShipping);
                mv.addObject("pids", pids);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/doEditShipping")
    public ModelAndView doEditShipping(@RequestParam("pids") String pids, UserShipping userShipping, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/order/toOrder.html");
        try {
            userShippingService.updateUserShipping(userShipping);
            mv.addObject("pids", pids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/doDeleteShipping")
    public ModelAndView doDeleteShipping(@RequestParam("pids") String pids, @RequestParam("userShippingId") long userShippingId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/order/toOrder.html");
        try {
            userShippingService.deleteUserShippingById(userShippingId);
            mv.addObject("pids", pids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }



//    @RequestMapping(value = "orderinfo.html", method = RequestMethod.GET)
//    public ModelAndView toOrder() {
//        return new ModelAndView("orderinfo");
//    }

}
