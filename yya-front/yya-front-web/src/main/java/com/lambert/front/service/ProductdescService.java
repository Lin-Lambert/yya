package com.lambert.front.service;

import com.lambert.manager.mapper.ProductDescMapper;
import com.lambert.manager.pojo.Productdesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductdescService {

    @Autowired
    private ProductDescMapper productDescMapper;


    public Productdesc queryProductdescByProductId(Long productId) {
        Productdesc productdesc = new Productdesc();
        productdesc.setId(productId);
        return productDescMapper.selectOne(productdesc);
    }
}
