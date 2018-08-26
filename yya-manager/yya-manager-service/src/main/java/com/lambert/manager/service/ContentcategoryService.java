package com.lambert.manager.service;

import com.lambert.manager.pojo.Contentcategory;

public interface ContentcategoryService extends BaseService<Contentcategory>{
    void deleteContentcategoryById(Contentcategory contentcategory);
}
