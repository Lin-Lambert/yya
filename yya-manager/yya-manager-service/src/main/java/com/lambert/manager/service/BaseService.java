package com.lambert.manager.service;

import java.util.List;

public interface BaseService<T> {

    public List<T> queryAll();

    public T queryById(Long id);

    public List<T> queryByWhere(T t);

    public Integer queryByWhereCount(T t);

    public List<T> queryByPage(Integer pageNo, Integer pageSize);

    public T queryOne(T t);

    public void save(T t);

    public void saveSelective(T t);

    public void updateById(T t);

    public void updateSelective(T t);

    public void deleteById(Long id);

    public void deleteByIds(List<Object> ids);

}
