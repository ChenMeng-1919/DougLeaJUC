package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 17:53
 * @description:
 * 从整体上来看Object的wait和notify/notify是与对象监视器配合完成线程间的等待/通知机制，
 * 而Condition与Lock配合完成等待通知机制，前者是java底层级别的，后者是语言级别的，
 * 具有更高的可控制性和扩展性。两者除了在使用方式上不同外，在功能特性上还是有很多的不同：
 * 1、Condition能够支持不响应中断，而通过使用Object方式不支持
 * 2、Condition能够支持多个等待队列（new 多个Condition对象），而Object方式只能支持一个
 * 3、Condition能够支持超时时间的设置，而Object不支持
 * 4、Condition由ReentrantLock对象创建，并且可以同时创建多个，
 * Condition接口在使用前必须先调用ReentrantLock的lock()方法获得锁，
 * 之后调用Condition接口的await()将释放锁，并且在该Condition上等待，
 * 直到有其他线程调用Condition的signal()方法唤醒线程，使用方式和wait()、notify()类似。
 */
@Slf4j
public class Demo2 {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            log.info(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                log.info(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            log.info(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            log.info(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                log.info(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.signal();
                log.info(System.currentTimeMillis() + "," + this.getName() + " signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e
                ) {
                    e.printStackTrace();
                }
                log.info(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
            } finally {
                lock.unlock();
            }
            log.info(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
    }
}
