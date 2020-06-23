package com.aaa.controller;

import com.aaa.entity.Result;
import com.aaa.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
public class UserController extends BaseController{

    @Autowired
    private MenuService menuService;
    
    /**
     * create by: xiaoliu
     * description: shiro登录
     * create time: 2020/6/19 20:32
     *
      * @Param: null
     * @return 
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();

        //已经认证过了就返回已经认证实体
        if (subject.isAuthenticated()) {
            session.setAttribute("menuTree", menuService.selectMuneByUserName(username));
            session.setAttribute("loginName",username );
            return super.success("已经认证",null);
        } else {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                session.setAttribute("menuTree", menuService.selectMuneByUserName(username));
                session.setAttribute("loginName",username );
            } catch (UnknownAccountException e) {
                return super.error(false, "账号不存在");
            } catch (IncorrectCredentialsException e) {
                return super.error(false, "密码不正确");
            } catch (ExcessiveAttemptsException e) {
                return super.error(false, "认证次数过多");
            } catch (Exception e) {
                return super.error(false, "未知异常");
            }

            return super.success("登录成功",null);
        }

    }
}

