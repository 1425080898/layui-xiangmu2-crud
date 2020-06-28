package com.aaa.service;

import com.aaa.entity.Result;
import com.aaa.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
public interface UserService extends IService<User> {
    List<Map> selectUserAndDept(String phonenumber,String loginName);
    boolean insertUserAndUserRole(User user);
    boolean updateUserAndUserRole(List<Map> mapList);
    User updateUnniquenessCheckByLoginName(String loginName,Integer userId);
}
