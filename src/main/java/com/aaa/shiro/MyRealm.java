package com.aaa.shiro;

import com.aaa.entity.User;
import com.aaa.service.MenuService;
import com.aaa.service.RoleService;
import com.aaa.service.UserService;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: 陈建
 * @Date: 2020/5/25 0025 16:33
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    /**
     * create by: xiaoliu
     * description: 注入service层
     * create time: 2020/6/18 15:42
     *
      * @Param: null
     * @return
     */
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * shiro安全框架的授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权");
        //获取当前用户信息
        User user = (User) principals.getPrimaryPrincipal();
        System.out.println(user.getUserId() + "-----" + user.getUserName());
        //查询用户拥有的角色和权限
        List<String> roleInfo = roleService.selectRoleByUserId(user.getUserId());
        System.out.println(roleInfo);
        List<String> perms = menuService.selectPermsByName(user.getUserId());
        System.out.println(perms);
        //将查询的结果添加到授权中去
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleInfo);
        info.addStringPermissions(perms);

        try {
            //不确定是什么原因导致权限可能会生成一个空值"", 会报错,所以将空值删除
            if (info != null && info.getStringPermissions() != null) {
                Set<String> permissions = info.getStringPermissions();
                for (String permission : permissions) {
                    if (StringUtils.isEmpty(permission)) {
                        permissions.remove(permission);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("移除空值权限出错---"+e.getMessage());
        }
        return info;
        //return null;
    }

    /**
     * shiro安全框架的认证,
     *
     * @param token
     * @return AuthenticationInfo  ，假如返回的是null就说明认证失败
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        //开始校验用户名和密码
        //取出令牌信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
      //登录验证分两个步骤，步骤一查询用户是否存在
        String username = usernamePasswordToken.getUsername();

        Wrapper<User> wrapper = new EntityWrapper<>();
        Wrapper<User> userWrapper = wrapper.eq("login_name", username);
        //根据username查询,如果查出来的实体为空就返回null 否则进行下一步
        User user = userService.selectOne(userWrapper);
        if (null == user) {
            return null;
        }
        //步骤二，查询密码是否正确
        //数据库中的密码
        String password = user.getPassword();
        /**
         *  * @param principal     认证主题    the 'primary' principal associated with the specified realm.
         *  * @param hashedCredentials 加密的一个凭证 the hashed credentials that verify the given principal.
         *  * @param credentialsSalt 盐值  the salt used when hashing the given hashedCredentials
         *  * @param realmName  当前realm的名字       the realm from where the principal and credentials were acquired.
         */
        //获取从数据库中的盐
        String salt = user.getSalt();
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        //构建了一个通用简单的一个认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, byteSource,getName());
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
