package com.aaa.util;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/19 20:09
 * @description：练习treeutil
 * @modified By：
 * @version:
 */
public class TreeUtil2 {

    public static List<LayUiTree> fromMenuListToLayUiTree(List<Menu> menuList) {
        //最终返回的集合
        List<LayUiTree> layUiTreeList = new ArrayList<>();
        //遍历循环集合
        for (Menu menu : menuList) {
            //如果parent==0的时候就说明是父亲
            if (menu.getParentId() == 0) {
                //是父亲就开始转换，把menu转换成layuitree
                LayUiTree layUiTree = setLayUiTree(menu);
                //孩子的参数是一个集合List<LayUiTree>
//                layUiTree.setChildren();
                layUiTreeList.add(setLayUiChildren(layUiTree, menuList));
            }
        }
        return layUiTreeList;
    }

    /**
     * create by: xiaoliu
     * description: 取出孩子
     * create time: 2020/6/19 20:15
     *
     * @return
     * @Param: null
     */
    public static LayUiTree setLayUiChildren(LayUiTree tree, List<Menu> menuList) {
        //把所有的子都放到一个集合中
        List<LayUiTree> layUiTreeList = new ArrayList<>();
        for (Menu menu : menuList) {
            //如果parentid == menuid 那么就是子
            if (tree.getId()==menu.getParentId()){
                //将子转换成layuitree
                LayUiTree layUiTree = setLayUiTree(menu);
                layUiTreeList.add(setLayUiChildren(layUiTree, menuList));
            }
        }
        tree.setChildren(layUiTreeList);
        return tree;
    }

    public static LayUiTree setLayUiTree(Menu menu){
        LayUiTree layUiTree = new LayUiTree();
        layUiTree.setTitle(menu.getMenuName());
        layUiTree.setId(menu.getMenuId());
        layUiTree.setUrl(menu.getUrl());
        return layUiTree;
    }
}
