package com.aaa.service;

import com.aaa.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
public interface RoleService extends IService<Role> {
    List<String> selectRoleByUserId(Integer userId);
}
