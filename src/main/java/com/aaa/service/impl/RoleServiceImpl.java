package com.aaa.service.impl;

import com.aaa.entity.Role;
import com.aaa.dao.RoleDao;
import com.aaa.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * create by: DaoChen
     * description: 根据当前用户查询拥有的角色信息
     * create time: 2020/6/25 11:47
     *
     No such property: code for class: Script1
     * @return
     */

    @Override
    public List<String> selectRoleByUserId(Integer userId) {
        return roleDao.selectRoleByUserId(userId);
    }
}
