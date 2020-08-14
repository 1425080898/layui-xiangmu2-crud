package com.aaa;

import com.aaa.dao.UserDao;
import com.aaa.entity.Dept;
import com.aaa.entity.LayUiTree;
import com.aaa.service.DeptService;
import com.aaa.service.MenuService;
import com.aaa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LayuiXiangmu2CrudApplicationTests {

    @Autowired
    DeptService deptService;
    @Autowired
    MenuService menuService;

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        List<Dept> depts = deptService.selectList(null);
        for (Dept dept : depts) {
            System.out.println(dept);
        }
    }

    @Test
    void menuList() {
        List<LayUiTree> layUiTreeList = menuService.selectMuneByUserName("admin");
        for (LayUiTree layUiTree : layUiTreeList) {
            System.out.println(layUiTree);
            List<LayUiTree> children = layUiTree.getChildren();
            for (LayUiTree child : children) {
                System.out.println(child);
                List<LayUiTree> children1 = child.getChildren();
                for (LayUiTree tree : children1) {
                    System.out.println(tree);
                }
            }
        }
    }

    @Test
    void aaa() {
        String str = new String("abcde");
        String replace = str.replace("c", "x");
        System.out.println(str);
        //声明数组
        int[] i = {1, 2, 3};
        int[] a = new int[]{};
    }
    @Test
    void ccc() {
//        //初始化jedis连接
//        Jedis jedis = new Jedis("192.168.88.100",6379);
//        //测试redis服务的连通性
//        String pong = jedis.ping();
//        //pong 是成功
//        System.out.println(pong);
    }

}
