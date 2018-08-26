package com.lambert.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.lambert.manager.mapper.CartMapper;
import com.lambert.manager.pojo.Cart;
import com.lambert.manager.pojo.Product;
import com.lambert.manager.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Value("${ATGUIGU_MANAGER_URL}")
    private String ATGUIGU_MANAGER_URL;

    @Autowired
    private HttpClientUtils httpClientUtils;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Cart> queryProductByIds(String pids) {
        List<Object> list = new ArrayList<>();
        String[] split = pids.split(",");
        for (String s : split) {
            list.add(Long.parseLong(s));
        }
        Example example = new Example(Cart.class);
        example.createCriteria().andIn("productId", list);
        return cartMapper.selectByExample(example);
    }

    public boolean addProductToCart(User user, Long productId, Integer num) throws Exception {

        Cart cart = new Cart();

        String productUrl = ATGUIGU_MANAGER_URL + "/restful/page/api/product/" + productId;
        String jsonProduct = httpClientUtils.doget(productUrl);

        if (jsonProduct != null) {
            Product product = MAPPER.readValue(jsonProduct, Product.class);
            cart.setProductTitle(product.getTitle());
            String[] images = product.getImages();
            if (images != null) {
                cart.setProductImage(images[0]);
            } else {
                cart.setProductImage(images[0]);
            }

            cart.setProductPrice(product.getPrice());
            cart.setNum(num);
        }

        //初始化
        cart.setCreated(new Date());
        cart.setUpdated(cart.getCreated());

        //判断购物车中是否有该商品,如果存在数量相加
        Cart param = new Cart();
        param.setUserId(user.getId());
        param.setProductId(productId);
        Cart selectCart = this.cartMapper.selectOne(param);
        if (selectCart != null) {
            //存在该商品
            selectCart.setNum(selectCart.getNum() + cart.getNum());
            selectCart.setUpdated(new Date());
            this.cartMapper.updateByPrimaryKeySelective(selectCart);
            return false;
        } else {
            //不存在该商品
            cart.setUserId(user.getId());
            cart.setProductId(productId);
            cart.setNum(num);
            this.cartMapper.insertSelective(cart);
            return true;
        }
    }

    public List<Cart> queryCartByUserId(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartMapper.select(cart);
    }

    public void updateNum(Long userId, Long productId, Integer num) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        Cart selectOne = cartMapper.selectOne(cart);
        if (selectOne == null) {
            return;
        }
        selectOne.setNum(num);
        selectOne.setUpdated(new Date());
        cartMapper.updateByPrimaryKeySelective(selectOne);
    }

    public void deleteCart(Long userId, Long productId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cartMapper.delete(cart);
    }
}
