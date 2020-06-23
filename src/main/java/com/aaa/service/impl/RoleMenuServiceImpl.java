package com.aaa.service.impl;

import com.aaa.entity.RoleMenu;
import com.aaa.dao.RoleMenuDao;
import com.aaa.service.RoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

}
