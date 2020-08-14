package com.aaa.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/7/2 14:50
 * @description：测试redis的类
 * @modified By：
 * @version:
 */
@SpringBootTest
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void setString(){
//        Userinfo userinfo = new Userinfo();
//        userinfo.setUsername("xiaozhang");
//        userinfo.setUserid(5);
//        redisTemplate.opsForValue().set("xiaoliu",userinfo);
    }
    @Test
    void getValue(){
//        Object xiaoliu = redisTemplate.opsForValue().get("xiaoliu");
//        System.out.println(xiaoliu);
    }
    @Test
    void setHash(){
        Map map = new HashMap();
        map.put("name", "koukou");
        map.put("age", 21);
        map.put("sex", "男");
//        redisTemplate.opsForHash().putAll("myHash", map);
//        Set myHash = redisTemplate.opsForHash().keys("myHash");
    }
    @Test
    void setTest(){
//        ValueOperations valueOperations = redisTemplateaa.opsForValue();
//        valueOperations.set("name2","12345");
//        System.out.println(valueOperations.get("name2"));
//        redisTemplate.opsForHash().putAll("myHash", map);
//        Set myHash = redisTemplate.opsForHash().keys("myHash");
    }
}
