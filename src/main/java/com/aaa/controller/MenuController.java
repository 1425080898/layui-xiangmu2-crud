package com.aaa.controller;


import com.aaa.dao.MenuDao;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Result;
import com.aaa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{


    @Autowired
    private MenuService menuService;

    /**
     * create by: xiaoliu
     * description: 查找所有的功能菜单
     * create time: 2020/6/18 16:34
     *
     * @Param: null
     * @return List<LayUiTree>
     */
    @RequestMapping("/findMenus")
    @ResponseBody
    public Result findMenus(){
        List<LayUiTree> layUiTreeList = menuService.selectMuneByUserName(null);
        if (layUiTreeList.size()>0&&layUiTreeList!=null){
            return super.success(layUiTreeList);
        }
        return super.error();
    }
}

