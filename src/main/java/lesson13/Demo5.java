package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 18:21
 * @description:await(long time, TimeUnit unit)超时之前被唤醒
 */
@Slf4j
public class Demo5 {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                log.info(System.currentTimeMillis() + "," + this.getName() + ",start");
                boolean r = condition.await(5, TimeUnit.SECONDS);
                log.info("{}", r);
                log.info(System.currentTimeMillis() + "," + this.getName() + ",end");
            } catch (InterruptedException e
            ) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        //休眠1秒之后，唤醒t1线程
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
