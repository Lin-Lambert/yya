package com.lambert.manager.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.manager.mapper.CategoryMapper;
import com.lambert.manager.pojo.Category;
import com.lambert.manager.service.CategoryService;
import com.lambert.manager.service.redis.utils.impl.RedisUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisUtilsImpl redisUtilsImpl;

    private static final String MANAGER_TREE_MENU="MANAGER_TREE_MENU";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<Category> queryAll(){

        //先命中
        try {
            String jsonData = this.redisUtilsImpl.get(MANAGER_TREE_MENU);
            if (jsonData!=null) {
                //在缓存中存在
                JavaType valueType = MAPPER.getTypeFactory().constructCollectionType(List.class, Category.class);
                List<Category> list = MAPPER.readValue(jsonData, valueType);
                return list;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<Category> list = this.categoryMapper.select(null);

        try {
            System.out.println(MAPPER.writeValueAsString(list));
            this.redisUtilsImpl.expire(MANAGER_TREE_MENU, MAPPER.writeValueAsString(list),60*60*24*30);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;

    }

}