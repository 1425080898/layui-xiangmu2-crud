package com.aaa.dao;

import com.aaa.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Repository
@Mapper
public interface RoleDao extends BaseMapper<Role> {
    int insertRole(Role role);

    List<Role> selectByIdAndRole(@Param("roleName") String roleName,@Param("roleId") Integer roleId);

    List<String> selectRoleByUserId(Integer userId);
}
