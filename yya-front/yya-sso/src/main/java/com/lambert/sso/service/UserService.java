package com.lambert.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.manager.mapper.UserMapper;
import com.lambert.manager.pojo.User;
import com.lambert.product.service.utils.RedisUtilsImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtilsImpl redisUtilsImpl;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Boolean check(String param, Integer type) throws Exception {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, true);
        map.put(2, true);
        map.put(3, true);
        if (!map.containsKey(type)) {
            throw new Exception("不合法");
        }
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(param);
                break;
            case 2:
                user.setPhone(param);
                break;
            case 3:
                user.setEmail(param);
                break;
        }
        int count = userMapper.selectCount(user);
        if (count > 0) {
            return false;
        }
        return true;
    }

    public void register(User user) {
        //校验用户信息合法性 TODO
        user.setId(null);
        //加密密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userMapper.insertSelective(user);
    }

    public String login(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        User selectOne = userMapper.selectOne(user);
        if (selectOne != null) {
            if (!StringUtils.equals(DigestUtils.md5Hex(password), selectOne.getPassword())) {
                return null;
            }
            String ticket = DigestUtils.md5Hex(username+System.currentTimeMillis());
            redisUtilsImpl.expire(ticket, MAPPER.writeValueAsString(selectOne), 1800);
            return ticket;
        } else {

            return null;
        }
    }

    public User queryByTicket(String ticket) throws Exception {
        String jsonData = this.redisUtilsImpl.get(ticket);
        if (jsonData!=null) {
            User user = MAPPER.readValue(jsonData, User.class);

            //刷新session有效时间
            this.redisUtilsImpl.expire(ticket, 1800);
            return user;
        }

        return null;
    }

    public void logout(String cookieValue) {
        this.redisUtilsImpl.del(cookieValue);
    }
}
