package com.aaa.service.impl;

import com.aaa.entity.Dept;
import com.aaa.dao.DeptDao;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
