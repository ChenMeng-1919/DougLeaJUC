package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 18:13
 * @description:
 */
@Slf4j
public class Demo3 {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();


    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e
            ) {
                log.info("中断标志：" + this.isInterrupted());
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
        TimeUnit.SECONDS.sleep(2);
        //给t1线程发送中断信号
        log.info("1、t1中断标志：" + t1.isInterrupted());
        t1.interrupt();
        log.info("2、t1中断标志：" + t1.isInterrupted());
    }
}
