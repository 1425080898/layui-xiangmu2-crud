package com.aaa.dao;

import com.aaa.entity.RoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Repository
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenu> {

}
