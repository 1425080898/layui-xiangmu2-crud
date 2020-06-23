package com.aaa.controller;

import com.aaa.entity.Result;
import com.aaa.util.Constants;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/23 15:27
 * @description：通用返回实体的controller
 * @modified By：
 * @version:
 */
public class BaseController {

    /**
     * create by: xiaoliu
     * description: 操作成功带参数
     * create time: 2020/6/23 15:35
     *
      * @Param: Object
     * @return Result
     */
    public Result success(Object object){
        Result result = new Result();
        result.setStatus(Constants.OPERATION_SUCCESS_CODE);
        result.setMsg(Constants.OPERATION_SUCCESS_INFORMATION);
        result.setData(object);
        return result;
    }
    public Result success(String msg,Object object){
        Result result = new Result();
        result.setStatus(Constants.OPERATION_SUCCESS_CODE);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    /**
     * create by: xiaoliu
     * description: 操作成功不带参
     * create time: 2020/6/23 15:36
     *
      * @Param: null
     * @return Result
     */
    public Result success(){
        return success(null);
    }

    /**
     * create by: xiaoliu
     * description:操作失败提示默认信息
     * create time: 2020/6/23 15:36
     *
      * @Param: null
     * @return
     */

    public Result error(){
        Result result = new Result();
        result.setStatus(Constants.OPERATION_FAILURE_CODE);
        result.setMsg(Constants.OPERATION_FAILURE_INFORMATION);
        return result;
    }
    
    /**
     * create by: xiaoliu
     * description: 失败
     * create time: 2020/6/23 15:39
     *
      * @Param: null
     * @return 
     */
    public Result error(boolean status,String msg){
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(msg);
        return result;
    }
}
