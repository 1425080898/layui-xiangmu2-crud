package com.aaa.controller;


import com.aaa.entity.*;
import com.aaa.service.MenuService;
import com.aaa.service.RoleMenuService;
import com.aaa.service.RoleService;
import com.aaa.util.Constants;
import com.aaa.util.TreeUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping("/selectRoleAll")
    public LayUiTable selectRoleAll(Integer page, Integer limit,String roleName, String roleKey){
        Wrapper<Role> wrapper = new EntityWrapper<>();
        if (roleName!=null&&!"".equals(roleName)){
            wrapper.eq("role_name",roleName);
        }
        if (roleKey!=null&&!"".equals(roleKey)){
            wrapper.eq("role_key",roleKey);
        }
        wrapper.eq("del_flag",0);
        Page<Role> pages = new Page<>(page,limit);

        System.out.println(roleName);
        System.out.println(roleKey);
        Page<Role> rolePage = roleService.selectPage(pages, wrapper);
        //获取分页后的内容
        List<Role> records = rolePage.getRecords();
        //查询数据库总条数
        int count = roleService.selectCount(wrapper);
        LayUiTable layUiTable = new LayUiTable(0, Constants.OPERATION_SUCCESS_INFORMATION,count,records);
        return layUiTable;
    }


    /**
     * create by: Super 张
     * description: 新增
     * create time: 2020/6/27 21:06
     *
      * @Param: null
     * @return
     */

    @RequestMapping("/saveRole")
    public Result saveOrUpdateRole(@RequestBody Map map) throws Exception {

        String roleInfo = JSON.toJSONString(map.get("roleInfo"));
        Role role = JSON.parseObject(roleInfo,Role.class);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        role.setCreateBy(user.getLoginName());
        role.setCreateTime(new Date());
        String checkData = JSON.toJSONString(map.get("checkData"));
        List<LayUiTree> layUiTreeList = JSON.parseArray(checkData, LayUiTree.class);
        //新增的顺序是先新增角色，2.在新增角色中间表
        int i = roleService.insertRole(role,layUiTreeList);
        if (i>0){
            return super.success();
        }else {
            return super.error();
        }
    }


    /**
     * create by: Super 张
     * description: 修改
     * create time: 2020/6/27 21:06
     *
      * @Param: null
     * @return
     */

    @RequestMapping("/updateRole")
    public Result updateRole(@RequestBody Map map) throws Exception {
        String roleInfo = JSON.toJSONString(map.get("roleInfo"));
        Role role = JSON.parseObject(roleInfo,Role.class);
        //添加修改时间
        role.setUpdateTime(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //添加修改人
        role.setUpdateBy(user.getLoginName());
        String checkData = JSON.toJSONString(map.get("checkData"));
        System.out.println(checkData);
        String substring = checkData.substring(1, checkData.length() - 2);
        System.out.println(substring);
        List<LayUiTree> layUiTreeList = JSON.parseArray(checkData, LayUiTree.class);
        System.out.println(layUiTreeList);
        //修改角色1.修改角色表，二删除角色权限中间表 三 新增中间表
        System.out.println(role);
        int i = roleService.updateRole(role, layUiTreeList);
        if (i>0){
            return super.success();
        }else {
            return super.error();
        }
    }


    /**
     * create by: Super 张
     * description: 删除
     * create time: 2020/6/27 21:06
     *
      * @Param: null
     * @return
     */
    @RequestMapping("/deleteRole")
    public Result deleteRole(Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setDelFlag("1");
        boolean b = roleService.updateById(role);
        Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id",roleId);
        boolean delete = roleMenuService.delete(wrapper);
        if (delete){
            return super.success();
        }else {
            return super.error();
        }
    }


    /**
     * create by: Super 张
     * description:
     * create time: 2020/6/27 21:05
     *
      * @Param: null
     * @return
     */

    @RequestMapping("/deleteRoles")
    public Result deleteRoles(@RequestBody List<Role> roleList) {
        for (Role role : roleList) {
            role.setDelFlag("1");
        }
        boolean b = roleService.updateBatchById(roleList);
        List<Integer> roleIds = new ArrayList<>();
        for (Role role:roleList) {
            roleIds.add(role.getRoleId());
        }
        boolean delete = roleMenuService.deleteBatchIds(roleIds);
        if (delete){
            return super.success();
        }else {
            return super.error();
        }
    }

    /**
     * create by: Super 张
     * description: id查询
     * create time: 2020/6/27 21:06
     *
      * @Param: null
     * @return
     */

    @RequestMapping("/selectRoleById")
    public Map selectRoleById(Integer roleId){
        System.out.println(roleId);
        //分别查询内容1.先查询role表  2.在查询中间表role_menuid
        Role role = roleService.selectById(roleId);
        //查询所有的权限
        List<LayUiTree> layUiTreeList = menuService.selectMuneByUserName(null);
        //查询中间表存储的权限角色绑定的id
        Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("role_id",roleId);
        List<RoleMenu> roleMenus = roleMenuService.selectList(wrapper);
        TreeUtil.selectAll(layUiTreeList,roleMenus);
        Map map = new HashMap(16);
        map.put("role",role);
        map.put("layUiTreeList",layUiTreeList);
        return map;
    }


    @RequestMapping("/selectByRoleName")
    public Result selectByRoleName(String roleName){
        Wrapper<Role> wrapper = new EntityWrapper<>();
        wrapper.eq("role_name",roleName);
        List<Role> roles = roleService.selectList(wrapper);
        System.out.println(roles);
        if (roles!=null&&roles.size()>0){
            return super.success();
        }else {
            return super.error();
        }
    }

    /**
     * create by: Super 张
     * description: 修改校验
     * create time: 2020/6/28 9:43
     *
      * @Param: null
     * @return
     */

    @RequestMapping("/selectByIdAndRole")
    public Result selectByIdAndRole(String roleName,Integer roleId){
        System.out.println(roleId);
        System.out.println(roleName);
        List<Role> roles = roleService.selectByIdAndRole(roleName, roleId);
        System.out.println(roles);
        if (roles!=null&&roles.size()>0){
            return super.error();
        }else {
            return super.success();
        }
    }
}



