package com.aaa.dao;

import com.aaa.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author Mr.Liu
 * @since 2020-06-13
 */
@Repository
@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> selectMuneByUserName(@Param("loginName") String loginName);

    public List<String> selectPermsByName(Integer userId);
    List<Menu> selectParent();
}
