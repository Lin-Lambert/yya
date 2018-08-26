package com.lambert.product.service.utils;

public interface Function<E,T> {

    public T callback(E e);
}
