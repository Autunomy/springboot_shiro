package com.hty.springboot_shiro.service;

import com.hty.springboot_shiro.entity.Pers;
import com.hty.springboot_shiro.entity.Role;
import com.hty.springboot_shiro.entity.User;

import java.util.List;

public interface UserService {
    void registry(User user);

    User findByUserName(String username);

    User findRolesByUserName(String username);

    List<Pers> findPersByRoleId(Integer id);
}
