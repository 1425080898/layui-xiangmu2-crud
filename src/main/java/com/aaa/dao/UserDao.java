package com.aaa.dao;

import com.aaa.entity.Menu;
import com.aaa.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    List<Map> selectUserAndDept(@Param("phonenumber") String phonenumber, @Param("loginName") String loginName);
    //修改loginname唯一性校验
    User updateUnniquenessCheckByLoginName(@Param("loginName") String loginName,@Param("userId") Integer userId);
}
