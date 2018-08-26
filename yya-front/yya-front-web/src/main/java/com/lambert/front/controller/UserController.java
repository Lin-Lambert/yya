package com.lambert.front.controller;

import com.lambert.cart.service.CartService;
import com.lambert.common.utils.CookieUtils;
import com.lambert.front.service.CookieCartService;
import com.lambert.manager.pojo.Cart;
import com.lambert.manager.pojo.User;
import com.lambert.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private CartService cartService;

    private static final String YOUYIJIA_TICKET = "YOUYIJIA_TICKET";

    @RequestMapping(value = "doreg", method = RequestMethod.POST)
    public String doreg(User user) {
        try {
            this.userService.register(user);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/reg.html";
    }

    @RequestMapping(value = "dologin", method = RequestMethod.POST)
    public String dologin(@RequestParam("loginname") String loginname, @RequestParam("nloginpwd") String nloginpwd, HttpServletRequest request, HttpServletResponse response) {
        try {
            String ticket = userService.login(loginname, nloginpwd);
            if (ticket != null && ticket != null && ticket.length() != 0) {

                CookieUtils.setCookie(request, response, YOUYIJIA_TICKET, ticket, true);

                User user = userService.queryByTicket(ticket);

                List<Cart> cartList = cookieCartService.queryCartList(request, response);

                if (cartList.size() != 0) {
                    for (Cart cart : cartList) {
                        for (int i = 0;i < cart.getNum();i++) {
                            cartService.addProductToCart(user, cart.getProductId(), 1);
                        }
                    }
                }

                CookieUtils.deleteCookie(request, response, "CART_TICKET");
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/login.html";
    }

    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = CookieUtils.getCookieValue(request, YOUYIJIA_TICKET, true);
        if (cookieValue != null && cookieValue != "" && cookieValue.length() != 0) {
            userService.logout(cookieValue);
        }
        CookieUtils.deleteCookie(request, response, YOUYIJIA_TICKET);
        return new ModelAndView("redirect:/");
    }

    /**
     * 根据ticket查询用户信息
     */
    @RequestMapping(value = "{ticket}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> queryByTicket(@PathVariable("ticket") String ticket) {

        try {
            User user = this.userService.queryByTicket(ticket);

            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ResponseBody
    @RequestMapping(value = "check/{param}/{type}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> check(@PathVariable("param") String param, @PathVariable("type") Integer type) {
        Boolean bool = false;
        try {
            bool = userService.check(param, type);
            return ResponseEntity.status(HttpStatus.OK).body(bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bool);
    }

    @RequestMapping("login.html")
    public ModelAndView toLogin() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "reg.html", method = RequestMethod.GET)
    public ModelAndView     toRegist() {
        return new ModelAndView("reg");
    }
}
