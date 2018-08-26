package com.lambert.manager.service.impl;

import com.lambert.manager.mapper.ContentcategoryMapper;
import com.lambert.manager.pojo.Contentcategory;
import com.lambert.manager.service.ContentcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentcategoryServiceImpl extends BaseServiceImpl<Contentcategory> implements ContentcategoryService {
    @Autowired
    private ContentcategoryMapper contentcategoryMapper;

    @Override
    public void deleteContentcategoryById(Contentcategory contentcategory) {
        List<Object> deleteIds = new ArrayList<Object>();
        deleteIds.add(contentcategory.getId());

        //递归查询所有子类目
        findSubNode(deleteIds, contentcategory.getId());

        //执行批量删除
        this.deleteByIds(deleteIds);

    }

    private void findSubNode(List<Object> deleteIds, Long id) {
        Contentcategory contentcategory = new Contentcategory();
        contentcategory.setParentid(id);
        //查询当前分类的子类目
        List<Contentcategory> list = this.contentcategoryMapper.select(contentcategory);

        for (Contentcategory contentcategory2 : list) {
            //把子类目的id放入集合中
            deleteIds.add(contentcategory2.getId());

            findSubNode(deleteIds, contentcategory2.getId());

        }

    }
}
