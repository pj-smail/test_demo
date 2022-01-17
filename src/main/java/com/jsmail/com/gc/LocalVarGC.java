package com.jsmail.com.gc;

import org.junit.Test;

/**
 * 使用-XX:+PrintGCDetails查看每个方法的GC情况
 */
public class LocalVarGC {

    public void LocalVarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];//10MB
        System.gc();
    }

    public void LocalVarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    public void LocalVarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public void LocalVarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    public void LocalVarGC5() {
        LocalVarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
        localVarGC.LocalVarGC2();
    }

}
