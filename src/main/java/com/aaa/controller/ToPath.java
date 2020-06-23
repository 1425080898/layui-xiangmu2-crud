package com.aaa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Demo class
 *
 * @author xiaoliu
 * @date 2020/6/18
 */
@Controller
public class ToPath {
    
    /**
     * create by: xiaoliu
     * description: 路径跳转
     * create time: 2020/6/18 13:53
     *
      * @Param: null
     * @return 
     */
    @RequestMapping("/toShowDept")
    public String deptIndex(){
        return "dept/showDept";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * create by: xiaoliu
     * description: 跳转到主页面
     * create time: 2020/6/18 13:59
     *
      * @Param: null
     * @return
     */
    @RequestMapping("/homePage")
    public String homePage(){
        return "homePage";
    }
    /**
     * create by: xiaoliu
     * description: shiro注销,注销完成之后跳转到登录页面
     * create time: 2020/6/18 13:59
     *
      * @Param: null
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping("/toShowMenu")
    public String toShowMenu(){
        return "menu/toShowMenu";
    }

}
