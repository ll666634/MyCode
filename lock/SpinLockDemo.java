package com.lenovo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * *************************
 *
 * @author : Administrator
 * @className: SpinLockDemo
 * @data : 2019/6/18 14:35:16
 * **********************
 *
 *  手写自旋锁
 */

public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t  invoked myUnLock()");
    }


    public static void main(String[] args) {

        SpinLockDemo spinLockDemo  = new SpinLockDemo();


        new Thread(() -> {
            spinLockDemo.mylock();


            //线程睡眠一会
            try{
                TimeUnit.SECONDS.sleep(5);
            }
            catch(InterruptedException e){
                   e.printStackTrace();
            }

            spinLockDemo.myUnlock();

        },"AA").start();


        //线程睡眠一会
        try{ TimeUnit.SECONDS.sleep(1); } catch(InterruptedException e){ e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.mylock();
            //线程睡眠一会
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch(InterruptedException e){
                   e.printStackTrace();
            }

            spinLockDemo.myUnlock();

        },"BB").start();

    }


}
