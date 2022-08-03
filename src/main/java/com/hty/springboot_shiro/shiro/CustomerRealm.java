package com.hty.springboot_shiro.shiro;

import com.hty.springboot_shiro.entity.Pers;
import com.hty.springboot_shiro.entity.Role;
import com.hty.springboot_shiro.entity.User;
import com.hty.springboot_shiro.service.UserService;
import com.hty.springboot_shiro.util.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

//定义Realm
public class CustomerRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //根据主身份信息获取角色信息和权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findRolesByUserName(primaryPrincipal);

        //授权角色
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles()) {
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<Pers> persByRoleId = userService.findPersByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(persByRoleId)){
                    for (Pers pers : persByRoleId) {
                        simpleAuthorizationInfo.addStringPermission(pers.getName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }


        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名信息
        String principal = (String) token.getPrincipal();

        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findByUserName(principal);

        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
