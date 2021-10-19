package lesson12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 14:36
 * @description:ReentrantLock获取锁的过程是可中断的
 * ReentrantLock提供了另外一种可能，
 * 就是在等的获取锁的过程中（发起获取锁请求到还未获取到锁这段时间内）是可以被中断的，
 * 也就是说在等待锁的过程中，程序可以根据需要取消获取锁的请求。
 *
 * ReentrankLock中必须使用实例方法 lockInterruptibly()获取锁时，在线程调用interrupt()方法之后，才会引发 InterruptedException异常

    线程调用interrupt()之后，线程的中断标志会被置为true

    触发InterruptedException异常之后，线程的中断标志有会被清空，即置为false

    所以当线程调用interrupt()引发InterruptedException异常，中断标志的变化是:false->true->false
 */
@Slf4j
public class Demo5 {

    private static ReentrantLock lock1 = new ReentrantLock(false);

    private static ReentrantLock lock2 = new ReentrantLock(false);

    public static class T extends Thread {

        int lock;

        public T(String name, int lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if (this.lock == 1) {
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                log.info("中断标志:{}", this.isInterrupted());
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    log.info("lock1解锁");
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    log.info("lock2解锁");
                    lock2.unlock();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            T t1 = new T("t1", 1);
            T t2 = new T("t2", 2);
            t1.start();
            t2.start();

            TimeUnit.SECONDS.sleep(5);
            t2.interrupt();
        }
    }
}
