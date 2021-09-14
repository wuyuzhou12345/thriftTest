package com.zhou.biz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhou.biz.constant.RedisKey;
import com.zhou.biz.entity.User;
import com.zhou.biz.mapper.UserMapper;
import com.zhou.thrift.GetUserVerifyInfoRequest;
import com.zhou.thrift.GetUserVerifyInfoResponse;
import com.zhou.thrift.IdentityStatus;
import com.zhou.thrift.TUserVerifyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserVerifyService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    final ObjectMapper objectMapper = new ObjectMapper();

    public GetUserVerifyInfoResponse getVerifyInfo(GetUserVerifyInfoRequest getUserVerifyInfoRequest) throws org.apache.thrift.TException {
        GetUserVerifyInfoResponse res = new GetUserVerifyInfoResponse();
        if(getUserVerifyInfoRequest.getUserId() == "zly"){
            TUserVerifyInfo info = new TUserVerifyInfo();
            info.setExtraInfo("zly-extra");
            info.setVerifyFirstClass("机构");
            info.setVerifySecondClass("学校");
            info.setIdentityStatus(IdentityStatus.IDENTIFIED);
            res.setUserVerifyInfo(info);
        }
        else{
            TUserVerifyInfo info = new TUserVerifyInfo();
            info.setExtraInfo("other-extra");
            info.setVerifyFirstClass("机构");
            info.setVerifySecondClass("医院");
            info.setIdentityStatus(IdentityStatus.NOT_IDENTIFIED);
            res.setUserVerifyInfo(info);
        }

        return res;
    }

    public boolean addUser(User user){
        try{
            userMapper.addOneUser(user);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }

    };

    public List<User> findAllUsers() throws IOException {
        List<User> users;
        //先从redis中取数据，如果redis中没有值读数据库中内容，然后再设置缓存回redis
        if(redisTemplate.opsForValue().get(RedisKey.REDIS_STRING_ALL_USERS) != null){
            Object getUsers = redisTemplate.opsForValue().get(RedisKey.REDIS_STRING_ALL_USERS);
            users = objectMapper.readValue(String.valueOf(getUsers), new TypeReference<List<User>>(){});
        }
        else{
            users = userMapper.findAllUsers();
            redisTemplate.opsForValue().set(RedisKey.REDIS_STRING_ALL_USERS, objectMapper.writeValueAsString(users),60*10, TimeUnit.SECONDS);
        }
        return users;
    }

    public boolean updateUser(User user){
        try{
            userMapper.updateUser(user);
            clearOneUserCache(user.getName());
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User getOneUser(String name){
        List<String> fields = new ArrayList<>();
        User user = new User();
        fields.add("name");
        fields.add("age");
        fields.add("no");
        //先从redis中取数据，如果redis中没有值读数据库中内容，然后再设置缓存回redis
        if(redisTemplate.opsForHash().get(RedisKey.REDIS_HASH_USERS + name,"name") != null){
            List content = redisTemplate.opsForHash().multiGet(RedisKey.REDIS_HASH_USERS + name,fields);
            user.setName(content.get(0).toString());
            user.setAge(Integer.parseInt(content.get(1).toString()));
            user.setNo(Long.parseLong((content.get(2).toString())));
        }
        else{
            user = userMapper.getOneUserDetail(name);
            Map<String,Object> data = new HashMap<>();
            data.put("name",user.getName());
            data.put("age",String.valueOf(user.getAge()));
            data.put("no",user.getNo().toString());
            redisTemplate.opsForHash().putAll(RedisKey.REDIS_HASH_USERS + name,data);
        }
        return user;
    }

    public boolean clearOneUserCache(String name){
        try {
            redisTemplate.delete(RedisKey.REDIS_HASH_USERS + name);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
