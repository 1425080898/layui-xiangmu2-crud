package com.aaa.dao;

import com.aaa.entity.Dept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Repository
@Mapper
public interface DeptDao extends BaseMapper<Dept> {
    public Dept selectDeptByNameAndId(Dept dept);

    public Dept selectDeptByName(Dept dept);
}
