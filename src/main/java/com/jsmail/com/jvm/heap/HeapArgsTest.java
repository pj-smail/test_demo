package com.jsmail.com.jvm.heap;

/**
 * 测试堆空间常用的jvm参数:
 * -XX: +PrintFlagsInitial :查看所有的参数的默认初始值
 * -XX:+PrintFLagsFinal :查看所有的参数的最终值（可能会存在修改，不再是初始值）
 * -Xms :初始堆空间内存(默认为物理内存的1/64)
 * -Xmx:最大堆空间内存（默认为物理内存的1/4)
 * -Xmn:设置新生代的大小。(初始值及最大值)
 * -XX:NewRatio:配置新生代与老年代在堆结构的占比
 * -XX:SurvivorRatio:设置新生代中Eden和so/s1空间的比例
 * -XX:MaxTenuringThreshold:设置新生代垃圾的最大年龄
 * -XX:+PrintGCDetails:输出详细的GC处理日志
 * 打印gc简要信息:-XX:+PrintGC   -verbose:gc
 * -XX:HandlePromotionFailure:是否设置空间分配担保
 *
 */
public class HeapArgsTest {

    public static void main(String[] args) {
        System.out.println("我是来打酱油的。。。");

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
