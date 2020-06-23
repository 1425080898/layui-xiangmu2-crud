package com.aaa.service.impl;

import com.aaa.entity.User;
import com.aaa.dao.UserDao;
import com.aaa.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
