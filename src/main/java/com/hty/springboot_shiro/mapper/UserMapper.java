package com.hty.springboot_shiro.mapper;

import com.hty.springboot_shiro.entity.Pers;
import com.hty.springboot_shiro.entity.Role;
import com.hty.springboot_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);

    User findByUserName(String username);

    //根据用户查询所有的权限
    User findRolesByUserName(String username);

    //根据角色id查询权限集合的方法
    List<Pers> findPersByRoleId(Integer id);
}
