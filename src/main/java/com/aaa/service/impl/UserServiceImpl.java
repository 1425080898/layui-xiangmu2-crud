package com.aaa.service.impl;

import com.aaa.dao.UserRoleDao;
import com.aaa.entity.User;
import com.aaa.dao.UserDao;
import com.aaa.entity.UserRole;
import com.aaa.service.UserService;
import com.aaa.shiro.ShiroUtil;
import com.aaa.util.MyUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Map> selectUserAndDept(String phonenumber,String loginName) {
        return userDao.selectUserAndDept(phonenumber,loginName);
    }

    @Override
    public boolean insertUserAndUserRole(User user) {
        //首先获取其中的盐，这个盐里存放了复选框的值，但不是数组，是1,2
        String checkValue = user.getSalt();

        //获取了之后给盐重新赋值
        String salt = UUID.randomUUID().toString();
        String password = user.getPassword();
        //重新加盐加密
        String newPassword = ShiroUtil.encryptionBySalt(salt, password);
        //设置加盐加密密码
        user.setPassword(newPassword);
        //加盐
        user.setSalt(salt);
        Integer insert = userDao.insert(user);
        System.out.println(insert);
        if (insert > 0) {
            //首先删除,如果账号角色表中有userid，就删除
            Wrapper wrapper = new EntityWrapper();
            wrapper.eq("user_id", user.getUserId());
            userRoleDao.delete(wrapper);
            String[] split = checkValue.split(",");
            for (String roleId : split) {
                //删除完之后新增
                if (MyUtil.checkStringWhetherIsEmpty(roleId)) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(Integer.parseInt(roleId));
                    userRole.setUserId(user.getUserId());
                    System.out.println(userRole);
                    userRoleDao.insert(userRole);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * create by: xiaoliu
     * description: 修改user表和userrole表
     * create time: 2020/6/27 15:11
     *
      * @Param: null
     * @return 
     */
    @Override
    public boolean updateUserAndUserRole(List<Map> mapList) {
        for (Map map : mapList) {
            //讲map中的user属性转换为user实体
            String userString = JSON.toJSONString(map.get("user"));
            User user = JSON.parseObject(userString, User.class);
            String roleIds = (String) map.get("roleIds");
            //拿到实体之后进行新增
            Integer i = userDao.updateById(user);
            if (i>0){
                //先根据用户id进行删除
                Wrapper<UserRole> wrapper = new EntityWrapper();
                wrapper.eq("user_id", user.getUserId());
                userRoleDao.delete(wrapper);
                //删除完之后，拿到集合中的
                String[] roleArray = roleIds.split(",");
                for (String  role: roleArray) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(user.getUserId());
                    userRole.setRoleId(Integer.parseInt(role));
                    userRoleDao.insert(userRole);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public User updateUnniquenessCheckByLoginName(String loginName, Integer userId) {
        return userDao.updateUnniquenessCheckByLoginName(loginName, userId);
    }
}
