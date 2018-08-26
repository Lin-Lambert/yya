package com.lambert.front.controller;

import com.lambert.front.bean.UserThreadLocal;
import com.lambert.cart.service.CartService;
import com.lambert.front.service.CookieCartService;
import com.lambert.manager.pojo.Cart;
import com.lambert.manager.pojo.User;
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
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "show.html")
    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("cart");
            if (user != null) {
                List<Cart> cartList = cartService.queryCartByUserId(user.getId());
                mv.addObject("cartList", cartList);
            } else {
                List<Cart> cartList = cookieCartService.queryCartList(request, response);
                mv.addObject("cartList", cartList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "add/{productId}", method = RequestMethod.GET)
    public String addProductToCart(@PathVariable("productId") Long productId, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "num", required = false,defaultValue = "1") Integer num) {
        User user = UserThreadLocal.get();
        try {
            if (user != null) {
                cartService.addProductToCart(user, productId, num);
            } else {
                cookieCartService.addProductToCart(request, response, productId, num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart/show.html";
    }

    @ResponseBody
    @RequestMapping(value = "/update/num/{productId}/{num}", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateNum(@PathVariable("productId") Long productId, @PathVariable("num") Long num, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        try {
            if (user != null) {
                cartService.updateNum(user.getId(), productId, num.intValue());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
            } else {
                cookieCartService.updateNum(request, response, productId, num);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    ///cart/delete/1473155182.html
    @RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
    public String deleteCart(@PathVariable("productId") Long productId, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        try {
            if (user != null) {
                cartService.deleteCart(user.getId(), productId);
            } else {
                cookieCartService.deleteCart(request, response, productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart/show.html";
    }

}
