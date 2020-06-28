package com.aaa.service;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
public interface RoleService extends IService<Role> {
    int insertRole(Role role,List<LayUiTree> layUiTreeList) throws Exception;

    int updateRole(Role role,List<LayUiTree> layUiTreeList) throws Exception;

    List<Role> selectByIdAndRole(String roleName,Integer roleId);
//    Map selectRoleById(Integer roleId);

    List<String> selectRoleByUserId(Integer userId);
}
