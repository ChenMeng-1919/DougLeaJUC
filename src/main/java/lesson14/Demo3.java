package lesson14;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 21:49
 * @description:唤醒方法在等待方法之前执行，线程无法被唤醒。
 * wait()/notify()/notifyAll()方法都必须放在同步代码（必须在synchronized内部执行）中执行，需要先获取锁

线程唤醒的方法（notify、notifyAll）需要在等待的方法（wait）之后执行，等待中的线程才可能会被唤醒，否则无法唤醒
 */
@Slf4j
public class Demo3 {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
                try {
                    //休眠3秒
                    lock.wait();
                } catch (InterruptedException e
                ) {
                    e.printStackTrace();
                }
                log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
            }
        });
        t1.setName("t1");
        t1.start();
        //休眠1秒之后唤醒lock对象上等待的线程
        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            lock.notify();
        }
        log.info("lock.notify()执行完毕");
    }
}
