package com.lambert.manager.service;

import com.lambert.manager.pojo.Product;

import java.util.List;

public interface ProductService extends BaseService<Product>{
    void saveProductAndDeac(Product product, String editorValue);

    List<Product> queryByIds(String pids);
}
