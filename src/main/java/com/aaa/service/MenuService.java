package com.aaa.service;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
public interface MenuService extends IService<Menu> {
    List<LayUiTree> selectMuneByUserName(String loginName);

    public List<String> selectPermsByName(Integer userId);
}
