package com.jsmail.com;

import com.jsmail.com.jdbc.dao.SysMenuDao;
import com.jsmail.com.jdbc.entity.SysMenuEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplicationTests {

    private SysMenuDao sysMenuDao;

    @Autowired
    public void setUserDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    @Test
    public void contextLoads() {
        List<SysMenuEntity> parentMenus = getParentMenu();
        List<SysMenuEntity> menuTrees = getChildMenu(parentMenus);
        System.out.println(menuTrees.size());
    }

    //获取以及菜单
    private List<SysMenuEntity> getParentMenu() {
        List<SysMenuEntity> parentMenus = sysMenuDao.selectMenu(0);
        return parentMenus;
    }

    //获取子菜单
    private List<SysMenuEntity> getChildMenu(List<SysMenuEntity> parentMenus) {
        AtomicReference<List<SysMenuEntity>> childMenus = new AtomicReference<>();
        parentMenus.forEach(parentMenu -> {
            childMenus.set(sysMenuDao.selectMenu(parentMenu.getId()));
            if(childMenus.get() != null) {
                parentMenu.setChildMenu(childMenus.get());
                getChildMenu(childMenus.get());
            }
        });
        return parentMenus;
    }

}
