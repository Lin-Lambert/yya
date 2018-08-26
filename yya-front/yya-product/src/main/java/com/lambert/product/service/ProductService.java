package com.lambert.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.manager.mapper.ProductMapper;
import com.lambert.manager.pojo.Product;
import com.lambert.product.service.utils.RedisUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private RedisUtilsImpl redisUtilsImpl;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    

    public Product queryProductById(Long productId) {
        Product product =null;
        try {
            String jsonData = this.redisUtilsImpl.get("ATGUIGU_PRODUCT_WEB_"+productId);
            
            if (jsonData!=null) {
                product = MAPPER.readValue(jsonData, Product.class);
                return product;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        product = this.productMapper.selectByPrimaryKey(productId);
        
        try {
            this.redisUtilsImpl.expire("ATGUIGU_PRODUCT_WEB_"+productId, MAPPER.writeValueAsString(product), 60*60*24);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return product;
    }

}
