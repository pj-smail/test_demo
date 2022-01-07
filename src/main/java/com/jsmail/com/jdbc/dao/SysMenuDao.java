package com.jsmail.com.jdbc.dao;

import com.jsmail.com.jdbc.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuDao {

    List<SysMenuEntity> selectMenu(Integer parentId);

}
