package com.lambert.manager.service.impl;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.lambert.manager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    private Class<T> clazz;

    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType)type;
        this.clazz = (Class<T>)ptype.getActualTypeArguments()[0];
    }

    @Override
    public List<T> queryAll() {
        return mapper.select(null);
    }

    @Override
    public T queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryByWhere(T t) {
        return mapper.select(t);
    }

    @Override
    public Integer queryByWhereCount(T t) {

        return mapper.selectCount(t);
    }

    @Override
    public List<T> queryByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = mapper.select(null);
//        PageInfo<T> pageInfo = new PageInfo<>(list);
        return list;
    }

    @Override
    public T queryOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public void save(T t) {
        mapper.insert(t);
    }

    @Override
    public void saveSelective(T t) {
        mapper.insertSelective(t);
    }

    @Override
    public void updateById(T t) {
        mapper.updateByPrimaryKey(t);
    }

    @Override
    public void updateSelective(T t) {
        mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(List<Object> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        mapper.deleteByExample(example);
    }
}