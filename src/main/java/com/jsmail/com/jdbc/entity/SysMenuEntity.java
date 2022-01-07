package com.jsmail.com.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_menu")
public class SysMenuEntity {

    private Integer id;

    private Integer parentId;

    private String menuName;

    private Integer sort;

    private List<SysMenuEntity> childMenu;

}
