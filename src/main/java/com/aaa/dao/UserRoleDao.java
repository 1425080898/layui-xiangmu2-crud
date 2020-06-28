package com.aaa.dao;

import com.aaa.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 * 判断
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Mapper
@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {
}
