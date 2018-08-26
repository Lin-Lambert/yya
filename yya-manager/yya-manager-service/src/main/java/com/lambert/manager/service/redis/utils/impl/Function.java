package com.lambert.manager.service.redis.utils.impl;

public interface Function<E, T> {

    public T callBack(E e);
}
