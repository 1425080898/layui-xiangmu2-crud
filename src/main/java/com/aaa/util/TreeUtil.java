package com.aaa.util;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/18 11:05
 * @description：树状实体类工具
 * @modified By：
 * @version: 1.0
 */
public class TreeUtil {

    /**
     * create by: xiaoliu
     * description: 把List<Menu>转换为List<LayUiTree>
     * create time: 2020/6/18 11:23
     *
      * @Param: menuList
     * @return List<LayUiTree>
     */
    public static List<LayUiTree> fromMenuListToLayUiTreeList(List<Menu> menuList){
        List<LayUiTree> layUiTreeList = new ArrayList<>();
        //遍历循环传入的菜单集合
        //如果当前集合的父id等于0那么就说明当前是父亲
        for (Menu menu : menuList) {
            //如果是0就是父 然后把menu转换为layuitree
            if (menu.getParentId()==0){
                //父的转换
                LayUiTree tree = fromMenuToTree(menu);
                //调用递归传入父，和总集合
                LayUiTree layUiTree = setTreeChildren(tree, menuList);
                layUiTreeList.add(layUiTree);
            }
        }
        return layUiTreeList;
    }
    /**
     * create by: xiaoliu
     * description: 进行递归，封装所有的孩子
     * create time: 2020/6/18 11:24
     *
     * @Param: LayUiTree,menuList
     * @return LayUiTree
     */
    public static LayUiTree setTreeChildren(LayUiTree tree,List<Menu> menuList){
        //这个集合封装了所有的孩子
        List<LayUiTree> childrenTreeList = new ArrayList<>();
        //遍历循环集合
        for (Menu menu : menuList) {
            //如果tree中的父id==menuid的时候就说明是孩子们
            if (menu.getParentId()==tree.getId()){
                //创建一个实体 把孩子放进去
                //menu转换tree
                LayUiTree childrenTree = fromMenuToTree(menu);
                //进行递归 childrenTreeList封装了所有的孩子
                //递归，还是要进行判断，如果parentid==menuid那么就是孩子，是孩子就需要添加
                childrenTreeList.add(setTreeChildren(childrenTree, menuList));
            }
        }
        tree.setChildren(childrenTreeList);
        return tree;
    }


    public static LayUiTree fromMenuToTree(Menu menu){
        LayUiTree childrenTree = new LayUiTree();
        //id赋值
        childrenTree.setId(menu.getMenuId());
        //名字赋值
        childrenTree.setTitle(menu.getMenuName());
        //路径赋值
        childrenTree.setUrl(menu.getUrl());
        //返回LayUiTree
        return childrenTree;
    }

}
