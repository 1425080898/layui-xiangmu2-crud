package com.aaa.service;

import com.aaa.entity.Dept;
import com.aaa.entity.Result;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
public interface DeptService extends IService<Dept> {
    public Result selectDeptByName(Dept dept);
}
