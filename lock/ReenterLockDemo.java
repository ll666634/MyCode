package com.lenovo.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
     public synchronized  void sendSMS() throws Exception{
         System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
         sendEmail();
     }
    public synchronized  void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t ****** invoked sendEmail()");
    }

    @Override
    public void run() {
        get();
    }
    Lock lock = new ReentrantLock();
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        }finally{
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ***********invoked set()");
        }finally{
            lock.unlock();
        }
    }
}








/**
 * *************************
 *
 * @author : Administrator
 * @className: ReenterLockDemo
 * @data : 2019/6/14 16:23:37
 *
 *
 *  可重入锁(又叫做递归锁) 最大作用是避免死锁
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 *      cas one synchronized 典型的可重入锁
 * t1	 invoked sendSMS()  t1线程在外层方法获取锁的时候
 * t1	 ****** invoked sendEmail()  t1线程在进入内层方法自动获取锁
 * t2	 invoked sendSMS()
 * t2	 ****** invoked sendEmail()
 *
 * **********************
 */
public class ReenterLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"t2").start();

        //线程睡眠一会
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();





        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();


    }







}
