package com.aaa.controller;


import com.aaa.dao.MenuDao;
import com.aaa.entity.LayUiTable;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.entity.Result;
import com.aaa.service.MenuService;
import com.aaa.util.Constants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
public class  MenuController extends BaseController{
    @Autowired
    private MenuService menuService;
    @RequestMapping("/selectTreeTable")
    public List<LayUiTree> selectTreeTable(){
        List<LayUiTree> layUiTreeList=menuService.selectMuneByUserName(null);
        return layUiTreeList;
    }
    @RequestMapping("/selectAllMenu")
    @ResponseBody
    public LayUiTable selectAllMenu(String searchMenuName, String visible, Model model){
        Wrapper<Menu> wrapper=new EntityWrapper<>();
        if (searchMenuName!=null&&!"".equals(searchMenuName)){
            wrapper.like("menu_name", searchMenuName);
        }
        if (visible!=null&&!"".equals(visible)){
            wrapper.like("visible", visible);
        } else {
            wrapper.eq("visible", 0);
        }
        List<Menu> menuList=menuService.selectList(wrapper);
        LayUiTable pageInfo=new LayUiTable();
        pageInfo.setData(menuList);
        pageInfo.setCount(menuList.size());
        pageInfo.setMsg(Constants.OPERATION_SUCCESS_INFORMATION);
        pageInfo.setCode(0);
        return pageInfo;
    }
    @RequestMapping("/saveMenu")
    @ResponseBody
    public Result saveMenu(Menu menu){
        System.out.println(menu.getMenuId());
        menu.setParentId(menu.getMenuId());
        boolean insert=menuService.insert(menu);
        if(insert){
            return new Result(true,"新增成功",null);
        }else{
            return new Result(false,"新增失败",null);
        }
    }
    @RequestMapping("/findAllMenusTreeTable")
    @ResponseBody
    public LayUiTable findAllMenusTreeTable(String searchMenuName,String searchvisible){
        System.out.println(searchMenuName);
        System.out.println(searchvisible);
        Wrapper<Menu> wrapper=new EntityWrapper<>();
        if (searchMenuName!=null&&!"".equals(searchMenuName)){
            wrapper.like("menu_name", searchMenuName);
        }
        if (searchvisible!=null&&!"".equals(searchvisible)){
            wrapper.like("visible", searchvisible);
        }else{
            wrapper.like("visible","0");
        }
//        wrapper.eq("visible",0);
        List<Menu> menuList=menuService.selectList(wrapper);
        LayUiTable pageInfo=new LayUiTable();
        pageInfo.setData(menuList);
        pageInfo.setCode(0);
        pageInfo.setMsg(Constants.OPERATION_SUCCESS_INFORMATION);
        pageInfo.setCount(menuList.size());
        return pageInfo;
    }
    @RequestMapping("/selectParent")
    @ResponseBody
    public List<Menu> selectParent(){
        return menuService.selectParent();
    }
    @RequestMapping("/selectParentName")
    @ResponseBody
    public Menu selectParentName(Integer parentId){
        Wrapper<Menu> wrapper=new EntityWrapper<>();
        wrapper.eq("menu_id",parentId);
        Menu menu=menuService.selectOne(wrapper);
        if(menu==null){
            Menu menu1=new Menu();
            menu1.setMenuName("主目录");
            return menu1;
        }else{
            return menuService.selectOne(wrapper);
        }

    }
    @RequestMapping("/updateMenu")
    @ResponseBody
    public Result updateMenu(Menu menu){
        System.out.println(menu);
        boolean i=menuService.updateById(menu);
        if(i){
            return super.success();
        }else{
            return super.error();
        }
    }
    @ResponseBody
    @RequestMapping("/deleteMenu")
    public Result deleteMenu(Integer menuId){
        Menu menu=new Menu();
        menu.setVisible("1");
        menu.setMenuId(menuId);
        boolean i=menuService.updateById(menu);
        if(i){
            return super.success();
        }else{
            return super.error();
        }
    }


}

