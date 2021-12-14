package com.jsmail.com.thread;

public class WaitAndNotifyDemo {

    private int num;

    public  synchronized void getEgg(){
        while(num==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println("拿鸡蛋");
        notify();
    }


    public synchronized void setEgg(){
        while(num!=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println("放鸡蛋");
        notify();
    }

    public static void main(String[] args) {
        WaitAndNotifyDemo set = new WaitAndNotifyDemo();
        Thread t1 = new GetEgeThread(set);
        Thread t2 = new Thread(new SetEggThread(set));
        t1.start();
        t2.start();
    }
}

class SetEggThread implements Runnable {
    private WaitAndNotifyDemo set;

    public SetEggThread(WaitAndNotifyDemo set) {
        this.set = set;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            set.setEgg();
        }
    }
}

class GetEgeThread extends Thread {
    private WaitAndNotifyDemo set;

    public GetEgeThread(WaitAndNotifyDemo set) {
        this.set = set;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            set.getEgg();
        }
    }
}
