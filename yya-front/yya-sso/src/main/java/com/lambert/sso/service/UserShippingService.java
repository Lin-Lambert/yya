package com.lambert.sso.service;

import com.github.abel533.entity.Example;
import com.lambert.manager.mapper.UserShippingMapper;
import com.lambert.manager.pojo.UserShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserShippingService {

    @Autowired
    private UserShippingMapper userShippingMapper;


    public List<UserShipping> queryUserShippingByUserId(Long id) {
        Example example = new Example(UserShipping.class);
        example.createCriteria().andEqualTo("userId", id);
        return userShippingMapper.selectByExample(example);
    }

    public void saveUserShipping(UserShipping userShipping) {
        userShippingMapper.insertSelective(userShipping);
    }

    public UserShipping queryUserShippingById(Long userShippingId) {
        return userShippingMapper.selectByPrimaryKey(userShippingId);
    }

    public void updateUserShipping(UserShipping userShipping) {
        userShippingMapper.updateByPrimaryKeySelective(userShipping);
    }

    public void deleteUserShippingById(long userShippingId) {
        userShippingMapper.deleteByPrimaryKey(userShippingId);
    }
}
