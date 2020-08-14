package com.aaa.redis;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

    //定义一个id，次id为mybatis namespace的命名空间
    String id;
    //声明读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    //储存值
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("保存数据----------");
        System.out.println("key---"+key+"-------"+"value---"+value);
        if (value!=null){
            getRedisTemplate().opsForValue().set(key.toString(),value);
        }
    }

    //获取值
    @Override
    public Object getObject(Object key) {
        System.out.println("获取值----------");
        System.out.println(key);
        if (key!=null){
            return getRedisTemplate().opsForValue().get(key.toString());
        }
        return null;
    }

    //删除
    @Override
    public Object removeObject(Object key) {
        System.out.println("删除值----------");
        if (key!=null){
            return getRedisTemplate().delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空----------");
        Set<String> keys = getRedisTemplate().keys("*:" + id + "*");
        for (String key : keys) {
            //删除
            System.out.println(key+"----------");
            getRedisTemplate().delete(key);
        }
    }

    @Override
    public int getSize() {
        Set<String> keys = getRedisTemplate().keys("*:" + id + "*");
        return keys.size();
    }

    //返回一个读写锁
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public RedisTemplate getRedisTemplate() {
        return (RedisTemplate) ApplicationHolder.getApplicationContextStatic().getBean("redisTemplate");
    }
}
