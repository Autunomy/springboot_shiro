package com.hty.springboot_shiro.service;

import com.hty.springboot_shiro.entity.Pers;
import com.hty.springboot_shiro.entity.Role;
import com.hty.springboot_shiro.entity.User;
import com.hty.springboot_shiro.mapper.UserMapper;
import com.hty.springboot_shiro.util.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void registry(User user) {
        //生成随机盐
        String salt = SaltUtil.getSalt(8);
        //将随机盐保存到数据库
        user.setSalt(salt);
        //明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public List<Pers> findPersByRoleId(Integer id) {
        return userMapper.findPersByRoleId(id);
    }
}
