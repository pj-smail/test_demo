package com.jsmail.com.jvm;

// import sun.security.ec.CurveDB;
// CurveDB是用来实现国密tls的依赖包。jdk 在1.8的不同版本中该包位置有变动，较低版本为sun.security.ec.CurveDB，
// 较高版本为sun.security.util.CurveDB。
import sun.security.util.CurveDB;

import java.net.URL;
import java.security.Provider;

public class CLassLoaderTest1 {

    public static void main(String[] args) {
        System.out.println("**********启动类加载器*********宋****");
        //获取BootstrapClassLoader能够加载的api的路径
        URL[ ] urls = sun.misc.Launcher.getBootstrapClassPath( ).getURLs();
        for (URL element : urls) {
            System.out.println(element.toExternalForm());
        }
        //从上面的路径中随意选择一个类,来看看他的类加载器是什么: 引导类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println("***********扩展类加载器*************");
        String extDirs = System.getProperty( "java.ext.dirs" );

        for (String path : extDirs.split(";")){
            System.out.println(path);
        }

        //从上面的路径中随意选择一个类,来看看他的类加载器是什么: 扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);
    }

}
