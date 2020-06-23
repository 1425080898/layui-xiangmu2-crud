package com.aaa.controller;

import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.Result;
import com.aaa.entity.Dept;
import com.aaa.entity.PageInfo;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController{

    @Autowired
    private DeptService deptService;

    @ResponseBody
    @RequestMapping("/deptIndex")
    public PageInfo deptIndex(Integer page, Integer limit,
                              String searchDeptName,String searchCreate,String searchUpdate) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        //条件查询 判断前台的值是否为空 不为空就like查询
        if (searchDeptName!=null&&!"".equals(searchDeptName)){
            wrapper.like("dept_name", searchDeptName);
        }
        if (searchDeptName!=null&&!"".equals(searchDeptName)){
            wrapper.like("dept_name", searchDeptName);
        }
        if (searchCreate!=null&&!"".equals(searchCreate)){
            wrapper.like("create_by", searchCreate);
        }
        if (searchUpdate!=null&&!"".equals(searchUpdate)){
            wrapper.like("update_by", searchUpdate);
        }
        wrapper.eq("del_flag", 0);
        /*
         * 1.分页查询
         * 2.逻辑删除显示0
         * 3.返回前台layuitable需要的json
         * */
        Page<Dept> deptInfo = new Page<>(page, limit);
        Page<Dept> deptPage = deptService.selectPage(deptInfo, wrapper);
        int i = deptService.selectCount(wrapper);
        List<Dept> records = deptPage.getRecords();
        PageInfo pageInfo = new PageInfo(0, "查询成功", i, records);
        return pageInfo;
    }


    @RequestMapping("/saveDept")
    @ResponseBody
    @SaveOrUpdateEntityAnn(entityClass = Dept.class)
    public Result saveDept(Dept dept) {
        boolean insert = deptService.insert(dept);
        if (insert) {
            return super.success();
        } else {
            return super.error();
        }
    }

    //修改
    @RequestMapping("/updateDep")
    @ResponseBody
    @SaveOrUpdateEntityAnn(entityClass = Dept.class)
    public Result updateDep(Dept dept){
        boolean i = deptService.updateById(dept);
        if (i){
            return super.success();
        }else {
            return super.error();
        }
    }



    @RequestMapping("/selectDepById")
    @ResponseBody
    public Result selectDepById(Integer deptId) {
        Dept dept = deptService.selectById(deptId);
        if (dept != null) {
            return super.success(dept);
        } else {
            return super.error();
        }
    }
    @RequestMapping("/delDep")
    @ResponseBody
    public Result delDep(Dept dept) {
        dept.setDelFlag("2");
        boolean i = deptService.updateById(dept);
        if (i){
            return super.success();
        }else {
            return super.error();
        }
    }

    /**
    * 批量删除
    * 1.拿到了一个数组，封装了所有的数据
    * */
    @RequestMapping("/delDepts")
    @ResponseBody
    public Result delDepts(@RequestBody List<Dept> deptList){
        List<Dept> deptListNew = new ArrayList<>();
        for (Dept dept : deptList) {
            Dept deptNew = new Dept();
            deptNew.setDelFlag("2");
            deptNew.setDeptId(dept.getDeptId());
            deptListNew.add(deptNew);
        }
        boolean i = deptService.updateBatchById(deptListNew);
        if (i){
            return super.success();
        }else {
            return super.error();
        }
    }
}

