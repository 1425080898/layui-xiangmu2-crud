package com.aaa.cache;

import com.aaa.entity.User;
import com.aaa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/7/2 15:38
 * @description：监控缓存
 * @modified By：
 * @version:
 */
@SpringBootTest
public class MybatisCache {

    @Autowired
    private UserService userService;

    @Test
    void findAllUser(){
        //获取当前系统毫秒数
        long start = System.currentTimeMillis();
        List<User> userList = userService.selectList(null);
//        System.out.println(userList.size());
        long end = System.currentTimeMillis();
        System.out.println("查询时间----------->"+(end-start));
    }

}
