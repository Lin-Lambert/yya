package com.lambert.front.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.cart.service.HttpClientUtils;
import com.lambert.common.utils.CookieUtils;
import com.lambert.manager.pojo.Cart;
import com.lambert.manager.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CookieCartService {

    @Value("${ATGUIGU_MANAGER_URL}")
    private String ATGUIGU_MANAGER_URL;

    @Autowired
    private HttpClientUtils httpClientUtils;

    private static final String CART_COOKIE_NAME = "CART_TICKET";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Cart> queryCartList(HttpServletRequest request, HttpServletResponse response) {

        String cookieValue = CookieUtils.getCookieValue(request, CART_COOKIE_NAME, true);

        JavaType valueType = MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class);

        List<Cart> carts = new ArrayList<>();
        try {
            if (cookieValue != null) {
                carts = MAPPER.readValue(cookieValue, valueType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carts;
    }

    public boolean addProductToCart(HttpServletRequest request, HttpServletResponse response, Long productId, Integer num) {
        try {
            List<Cart> carts = queryCartList(request, response);
            Cart cart = null;
            for (Cart c : carts) {
                if (c.getProductId().intValue() == productId.intValue()) {
                    cart = c;
                    break;
                }
            }
            if (cart == null) {
                cart = new Cart();
                String url = ATGUIGU_MANAGER_URL + "/restful/page/api/product/" + productId;
                String jsonData = httpClientUtils.doget(url);
                if (jsonData != null) {
                    Product product = MAPPER.readValue(jsonData, Product.class);
                    cart.setProductId(productId);
                    cart.setId(null);
                    cart.setProductTitle(product.getTitle());
                    String[] images = product.getImages();
                    if (images != null) {
                        cart.setProductImage(images[0]);
                    } else {
                        cart.setProductImage("");
                    }

                    cart.setCreated(new Date());
                    cart.setUpdated(cart.getCreated());
                    cart.setProductPrice(product.getPrice());
                    cart.setNum(num);

                    carts.add(cart);
                }
            } else {
                cart.setNum(cart.getNum() + num);
                cart.setUpdated(new Date());
            }
            CookieUtils.setCookie(request, response, CART_COOKIE_NAME, MAPPER.writeValueAsString(carts), true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNum(HttpServletRequest request, HttpServletResponse response, Long productId, Long num) {
        try {
            List<Cart> carts = queryCartList(request, response);
            for (Cart cart : carts) {
                if (cart.getProductId().intValue() == productId.intValue()) {
                    cart.setNum(num.intValue());
                    cart.setUpdated(new Date());
                    break;
                }
            }
            CookieUtils.setCookie(request, response, CART_COOKIE_NAME,  MAPPER.writeValueAsString(carts), true);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCart(HttpServletRequest request, HttpServletResponse response, Long productId) {
        try {
            List<Cart> carts = queryCartList(request, response);
            for (Cart cart : carts) {
                if (cart.getProductId().intValue() == productId.intValue()) {
                    carts.remove(cart);
                    break;
                }
            }
            CookieUtils.setCookie(request, response, CART_COOKIE_NAME, MAPPER.writeValueAsString(carts), true);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
