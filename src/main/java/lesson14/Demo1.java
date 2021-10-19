package lesson14;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 21:40
 * @description:使用Object类中的方法实现线程等待和唤醒
 */
@Slf4j
public class Demo1 {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
                try {
                    log.info("t1进入睡眠");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
            }
        });

        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        synchronized (lock) {
            log.info("唤醒睡眠线程t1");
            lock.notify();
        }
    }
}
