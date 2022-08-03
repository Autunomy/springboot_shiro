package com.hty.springboot_shiro.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

//自定义的shiro中的缓存管理器
public class RedisCacheManager implements CacheManager {
    //s表示认证或者授权缓存的统一名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);

        return new RedisCache<K,V>(cacheName);
    }
}
