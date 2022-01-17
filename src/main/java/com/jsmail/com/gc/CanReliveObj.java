package com.jsmail.com.gc;

/**
 * 测试Object类中finalize()方法，即对象的finalization机制
 */
public class CanReliveObj {

    public static CanReliveObj obj;//类变量，属于GC Root

    /**
     * 该方法只被调用一次
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;//当前待回收的对象在finalize()方法中与引用链上的任何一个对象obj建立了联系
    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            //对象第一次成功拯救自己
            obj = null;
            System.gc();//调用垃圾回收器
            System.out.println("第一次gc");
            Thread.sleep(2000);
            if(obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
            System.out.println("第二次gc");
            //下面这段代码与上面的完全相同，但是这次自救却失败了
            obj = null;
            System.gc();
            //因为Finalizer 线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if(obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (Exception e) {

        }
    }
}
