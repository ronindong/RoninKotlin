package com.ronin.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ConcurrentTest {

    public static void main(String[] args) {

//        testCountDownLatch();
//        testCyclicBarrier();
//        testSemaphore();

        testReentrantLock();
    }

    /**
     * ReentrantLock:同步锁，
     */
    public static void testReentrantLock() {
        int threadCount = 10;
        final int count = 10000;
        final SafeIncNumBean sib = new SafeIncNumBean();
        final CountDownLatch cdl = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i < count; i++) {
                        sib.inc();
                    }
                    System.out.println("finish index:"+index);
                    cdl.countDown();
                }
            }.start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count total:"+sib.getCount());

    }


    /**
     * Semaphore：和锁类似，一般用于资源的访问控制
     */
    public static void testSemaphore() {
        final int count = 8;
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < count; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        semaphore.acquire();
                        System.out.println("acquire:" + index);
                        Thread.sleep(2000);
                        System.out.println("release;" + index);
                        semaphore.release();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }


    /**
     * CyclicBarrier：一般用于一组线程相互等待某个状态，然后同时运行
     */
    public static void testCyclicBarrier() {
        final int count = 5;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                System.out.println("All thread run finish!");
            }
        });
        for (int i = 0; i < count; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    System.out.println("thread name:" + Thread.currentThread().getName() + "start");
                    try {
                        Thread.sleep(2000);
                        System.out.println("thread name:" + Thread.currentThread().getName() + "end");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //CyclicBarrier可以重用
        for (int i = 0; i < count; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    System.out.println("thread name:" + Thread.currentThread().getName() + "start");
                    try {
                        Thread.sleep(2000);
                        System.out.println("thread name:" + Thread.currentThread().getName() + "end");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


    }

    /**
     * CountDownLatch：一般用于某个线程等待其他若干个线程执行完后，开始执行
     */
    public static void testCountDownLatch() {
        int count = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread index:" + index);
                    countDownLatch.countDown();
                }
            }.start();
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All thread run finish!");

    }


}
