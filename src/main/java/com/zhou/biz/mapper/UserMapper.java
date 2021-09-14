package com.zhou.biz.mapper;

import com.zhou.biz.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void addOneUser(User user);
    List<User> findAllUsers();
    void updateUser(User user);
    User getOneUserDetail(String name);
}
