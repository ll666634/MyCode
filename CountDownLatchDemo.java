package com.lenovo.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * *************************
 *
 * @author : Administrator
 * @className: CountDownLatchDemo
 * @data : 2019/6/19 15:08:18
 * **********************
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();

        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t ******* 秦帝国，一统华夏");




    }

    private static void closeDrr() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
          new Thread(() -> {

              System.out.println(Thread.currentThread().getName() + "\t 下晚自习，同学出门");
              countDownLatch.countDown();
         },String.valueOf(i)).start();

        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t ******* 同学走完，班长关门");
    }
}
