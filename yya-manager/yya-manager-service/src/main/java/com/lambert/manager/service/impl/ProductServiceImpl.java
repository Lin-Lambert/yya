package com.lambert.manager.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.lambert.manager.mapper.ProductDescMapper;
import com.lambert.manager.mapper.ProductMapper;
import com.lambert.manager.pojo.Product;
import com.lambert.manager.pojo.Productdesc;
import com.lambert.manager.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDescMapper productDescMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void saveProductAndDeac(Product product, String editorValue) {
        productMapper.insertSelective(product);
        Productdesc productdesc = new Productdesc();
        productdesc.setId(product.getId());
        productdesc.setProductdesc(editorValue);
        productDescMapper.insertSelective(productdesc);
    }

    @Override
    public List<Product> queryByIds(String pids) {
        List<Object> list = new ArrayList<>();
        String[] split = pids.split(",");
        for (String s : split) {
            list.add(Long.parseLong(s));
        }
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id", list);
        return productMapper.selectByExample(example);
    }

    @Override
    public void deleteByIds(List<Object> ids) {
        for (Object id : ids) {
            sendMQ("delete", (Long)id);
        }
        Example example = new Example(Product.class);
        example.createCriteria().andIn("id", ids);
        productMapper.deleteByExample(example);
    }

    private void sendMQ(String delete, Long id) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("type", delete);
            map.put("productId", id);
//            rabbitTemplate.convertAndSend("product." + delete, MAPPER.writeValueAsBytes(map) );
            this.rabbitTemplate.convertAndSend("product." + delete, MAPPER.writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
