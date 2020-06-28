package com.aaa.controller;

import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.*;
import com.aaa.service.DeptService;
import com.aaa.service.MenuService;
import com.aaa.service.UserRoleService;
import com.aaa.service.UserService;
import com.aaa.util.Constants;
import com.aaa.util.MyUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * create by: xiaoliu
     * description: shiro登录
     * create time: 2020/6/19 20:32
     *
     * @return
     * @Param: null
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();

        //已经认证过了就返回已经认证实体
        if (subject.isAuthenticated()) {
            session.setAttribute("menuTree", menuService.selectMuneByUserName(username));
            session.setAttribute("loginName", username);
            return super.success("已经认证", null);
        } else {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                session.setAttribute("menuTree", menuService.selectMuneByUserName(username));
                session.setAttribute("loginName", username);
            } catch (UnknownAccountException e) {
                return super.error(false, "账号不存在");
            } catch (IncorrectCredentialsException e) {
                return super.error(false, "密码不正确");
            } catch (ExcessiveAttemptsException e) {
                return super.error(false, "认证次数过多");
            } catch (Exception e) {
                return super.error(false, "未知异常");
            }

            return super.success("登录成功", null);
        }
    }

    /**
     * create by: xiaoliu
     * description: 用户查询
     * create time: 2020/6/23 20:11
     *
     * @return
     * @Param: null
     */
    @RequestMapping("/selectUserAndDept")
    @ResponseBody
    public LayUiTable selectUserAndDept(Integer page, Integer limit,String phonenumber,String loginName) {
        //开启分页
        PageHelper.startPage(page, limit);
        //查询的数据
//        if (null != phonenumber && !"".equals(phonenumber)){
//
//        }
        List<Map> mapList = userService.selectUserAndDept(phonenumber,loginName);
        //创建pageinfo对象存储数据
        PageInfo<Map> mapPageInfo = new PageInfo<>(mapList);
        //返回前台table需要的数据
        LayUiTable layUiTable = new LayUiTable(0,
                Constants.OPERATION_SUCCESS_INFORMATION,
                (int) mapPageInfo.getTotal(),
                mapPageInfo.getList());
        return layUiTable;
    }

    /**
     * create by: xiaoliu
     * description: 获取部门下拉
     * create time: 2020/6/24 9:19
     *
     * @return
     * @Param: null
     */
    @GetMapping("/findDeptNames")
    @ResponseBody
    public Result findDeptNames() {
        List<Dept> deptList = deptService.selectList(null);
        if (MyUtil.checkArrayWhetherIsEmpty(deptList)) {
            return super.success(deptList);
        } else {
            return super.error();
        }
    }

    @RequestMapping("/insertUserAndUserRole")
    @ResponseBody
    @SaveOrUpdateEntityAnn(entityClass = User.class)
    public Result savetUserAndUserRole(User user) {
        boolean insert = userService.insertUserAndUserRole(user);
        if (insert) {
            return super.success();
        } else {
            return super.error();
        }
    }

    @RequestMapping("/selectUserById")
    @ResponseBody
    public Result selectUserById(Integer userId) {
        Map map = new HashMap(16);
        User user = userService.selectById(userId);
        map.put("user", user);
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("user_id", userId);
        List<UserRole> userRoleList = userRoleService.selectList(wrapper);
//        List<Map> userRoleList = userRoleService.userRoleCheckedSelectedByDefault(userId);
        map.put("userRoleList", userRoleList);
        if (MyUtil.checkEntityWhetherIsEmpty(map)) {
            return super.success(map);
        } else {
            return super.error();
        }
    }
    //updateUserAndUserRole

    @RequestMapping("/saveUserAndUserRole")
    @ResponseBody
//    @SaveOrUpdateEntityAnn(entityClass = User.class)
    public Result saveUserAndUserRole(@RequestBody List<Map> mapList) {
//    public void saveUserAndUserRole(User user,String roleIds){
        boolean updateUserAndUserRole = userService.updateUserAndUserRole(mapList);
        if (updateUserAndUserRole) {
            return super.success();
        } else {
            return super.error();
        }
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Result deleteUser(Integer userId) {
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        String loginName = user1.getLoginName();
        User user = new User();
        user.setUserId(userId);
        user.setDelFlag("2");
        user.setUpdateTime(new Date());
        user.setUpdateBy(loginName);
        boolean update = userService.updateById(user);
        if (update) {
            return super.success();
        } else {
            return super.error();
        }
    }

    /**
     * create by: xiaoliu
     * description: 批量删除
     * create time: 2020/6/27 21:28
     *
      * @Param: mapList
     * @return Result
     */
    @RequestMapping("/deleteBatchUser")
    @ResponseBody
    public Result deleteBatchUser(@RequestBody List<Map> mapList){
        System.out.println(mapList);
        //遍历循环集合
        List<User> userList = new ArrayList<>();
        for (Map map : mapList) {
            //获取每一个userid
            User user = new User();
            Object user_id = map.get("user_id");
            user.setUserId((Integer) user_id);
            userList.add(user);
        }
        for (User user : userList) {
            user.setDelFlag("2");
        }
        boolean update = userService.updateBatchById(userList);
        if (update){
            return super.success();
        }else {
            return super.error();
        }
    }
    /**
     * create by: xiaoliu
     * description: 根据名称唯一性校验
     * create time: 2020/6/27 22:00
     *
     * @Param: loginName
     * @return Result
     */

    @ResponseBody
    @RequestMapping("/uniquenessCheckByLoginName")
    public Result uniquenessCheckByLoginName(String loginName){
        Wrapper<User> wrapper = new EntityWrapper();
        wrapper.eq("login_name", loginName);
        List<User> list = userService.selectList(wrapper);
        if (MyUtil.checkArrayWhetherIsEmpty(list)){
            return super.success();
        }else {
            return super.error();
        }
    }
    //updateUnniquenessCheckByLoginName
    /**
     * create by: xiaoliu
     * description: 修改loginName唯一性校验
     * create time: 2020/6/28 9:13
     *
      * @Param: loginName,userId
     * @return Result
     */
    @ResponseBody
    @RequestMapping("/updateUnniquenessCheckByLoginName")
    public Result updateUnniquenessCheckByLoginName(String loginName,Integer userId){
        User user = userService.updateUnniquenessCheckByLoginName(loginName, userId);
        if (MyUtil.checkEntityWhetherIsEmpty(user)){
            return super.success();
        }else {
            return super.error();
        }
    }
    /**
     * create by: xiaoliu
     * description: 手机号唯一性校验
     * create time: 2020/6/28 9:33
     * @Param: null
     * @return
     */
    @ResponseBody
    @RequestMapping("/unniquenessCheckByPhone")
    public Result unniquenessCheckByPhone(String phoneNumber){
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("phonenumber", phoneNumber);
        List<User> userList = userService.selectList(wrapper);
        if (MyUtil.checkArrayWhetherIsEmpty(userList)){
            return super.success();
        }else {
            return super.error();
        }
    }

}

