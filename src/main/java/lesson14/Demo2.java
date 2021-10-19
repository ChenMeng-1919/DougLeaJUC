package lesson14;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 21:45
 * @description:删除了 synchronized关键字
 */
@Slf4j
public class Demo2 {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->
        {
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            try {
                lock.wait();
            } catch (InterruptedException e
            ) {
                e.printStackTrace();
            }
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
        });
        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        lock.notify();
    }
}
