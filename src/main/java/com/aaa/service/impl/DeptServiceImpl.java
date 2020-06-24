package com.aaa.service.impl;

import com.aaa.entity.Dept;
import com.aaa.dao.DeptDao;
import com.aaa.entity.Result;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {
    @Autowired
    private DeptDao deptDao;

    /**
     * create by: DaoChen
     * description: 部门名称唯一性校验
     * create time: 2020/6/24 10:13
     *
     No such property: code for class: Script1
     * @return
     */

    @Override
    public Result selectDeptByName(Dept dept) {
        Dept resultDept = null;

        //如果id不为空那么就是修改
        if (!"".equals(dept.getDeptId()) && dept.getDeptId() != null) {
            resultDept = deptDao.selectDeptByNameAndId(dept);
        // 否则就是新增
        }else {
            resultDept = deptDao.selectDeptByName(dept);
        }
        if (resultDept != null) {
            return new Result(false,"部门名称已存在",null);
        }else {
            return new Result(true,"部门名称可用",null);
        }
    }
}
