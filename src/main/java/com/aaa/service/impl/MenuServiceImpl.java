package com.aaa.service.impl;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.dao.MenuDao;
import com.aaa.service.MenuService;
import com.aaa.util.TreeUtil;
import com.aaa.util.TreeUtil2;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * create by: xiaoliu
     * description: 
     * create time: 2020/6/17 15:25
     *
      * @Param: null
     * @return 
     */
    @Override
    public List<LayUiTree> selectMuneByUserName(String loginName) {
        List<Menu> menuList = menuDao.selectMuneByUserName(loginName);
        List<LayUiTree> layUiTreeList = TreeUtil.fromMenuListToLayUiTreeList(menuList);
//        List<LayUiTree> layUiTreeList = TreeUtil2.fromMenuListToLayUiTree(menuList);
        return layUiTreeList;
    }

    /**
     * create by: DaoChen
     * description: 根据用户id查询它拥有的权限
     * create time: 2020/6/25 11:41
     *
     No such property: code for class: Script1
     * @return
     */

    @Override
    public List<String> selectPermsByName(Integer userId) {
        return menuDao.selectPermsByName(userId);
    }
}

