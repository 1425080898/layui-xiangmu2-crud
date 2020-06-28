package com.aaa.util;

import java.util.List;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/24 9:57
 * @description：判断类工具
 * @modified By：
 * @version:
 */
@SuppressWarnings({"ALL", "AlibabaCommentsMustBeJavadocFormat"})
public class MyUtil {
    /**
     * create by: xiaoliu
     * description: 检查集合是否为空
     * create time: 2020/6/24 14:16
     *
     * @Param: null
     * @return
     */
    public static boolean checkArrayWhetherIsEmpty(List<?> list){
        if (list != null && list.size()>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * create by: xiaoliu
     * description: 检查实体是否为空
     * create time: 2020/6/24 14:16
     *
     * @Param: null
     * @return
     */
    public static boolean checkEntityWhetherIsEmpty(Object object){
        if (object != null){
            return true;
        }else {
            return false;
        }
    }


    /**
     * create by: xiaoliu
     * description: 检查字符串是否为空
     * create time: 2020/6/24 14:16
     *
      * @Param: null
     * @return
     */
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    public static boolean checkStringWhetherIsEmpty(String str){
        if (null !=str && !"".equals(str)){
            return true;
        }else {
            return false;
        }
    }
}
