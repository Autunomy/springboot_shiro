package com.hty.springboot_shiro.util;

import java.util.Random;

public class SaltUtil {
    /***
     * 生成salt的静态方法
     * @param n 需要的盐的长度
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;++i){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
