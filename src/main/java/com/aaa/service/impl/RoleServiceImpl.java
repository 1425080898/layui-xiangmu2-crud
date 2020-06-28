package com.aaa.service.impl;

import com.aaa.dao.MenuDao;
import com.aaa.dao.RoleMenuDao;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.entity.Role;
import com.aaa.dao.RoleDao;
import com.aaa.entity.RoleMenu;
import com.aaa.service.MenuService;
import com.aaa.service.RoleMenuService;
import com.aaa.service.RoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public int insertRole(Role role,List<LayUiTree> layUiTreeList) throws Exception {
        System.out.println(role);
        int i = roleDao.insertRole(role);
        if(i<0){
            throw new Exception("错误");
        }
        childrenAll(role.getRoleId(),layUiTreeList);
        return 1;
    }

    @Override
    public int updateRole(Role role, List<LayUiTree> layUiTreeList) throws Exception {
        //修改角色表
        Integer integer = roleDao.updateById(role);
        //删除中间表
        if (integer<0){
            throw new Exception("修改角色表失败");
        }
        Integer integer1 = roleMenuDao.deleteById(role.getRoleId());
        childrenAll(role.getRoleId(),layUiTreeList);
        return 1;
    }

    @Override
    public List<Role> selectByIdAndRole(String roleName, Integer roleId) {
        return roleDao.selectByIdAndRole(roleName,roleId);
    }


//    @Override
//    public Map selectRoleById(Integer roleId) {
//        //先获取role表的内容
//        Role role = roleDao.selectById(roleId);
//        Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
//        wrapper.eq("role_id",roleId);
//        //查询role拥有的权限id
//        List<RoleMenu> roleMenus = roleMenuDao.selectList(wrapper);
//        //默认勾选  首先获取所有
//        List<Menu> menus = menuDao.selectList(null);
//        //这个使全部的权限
//        for (int i = 0; i <menus.size() ; i++) {
//            Menu menu = menus.get(i);
//            //这个使拥有的权限
//            for (int j = 0; j <roleMenus.size() ; j++) {
//                RoleMenu roleMenu = roleMenus.get(i);
//                if (menu.getMenuId().equals(roleMenu.getMenuId())){
//
//                }
//            }
//        }
//
//        Map map = new HashMap();
//        map.put("role",role);
//        map.put("roleMenus",roleMenus);
//        return map;
//    }




    public void childrenAll(Integer roleId,List<LayUiTree> layUiTreeList){
        for (LayUiTree layUiTree : layUiTreeList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(layUiTree.getId());
            roleMenu.setRoleId(roleId);
            System.out.println(roleId);
            Integer insert = roleMenuDao.insert(roleMenu);
            if (layUiTree.getChildren()!=null){
                this.childrenAll(roleId,layUiTree.getChildren());
            }
        }
    }



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
