package lesson14;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/*
 * @author: cm
 * @date: Created in 2021/11/1 11:22
 * @description:park()让线程等待之后，是否能够响应线程中断？
 */
@Slf4j
public class Demo9 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start!");
            log.info(Thread.currentThread().getName() + ",park()之前中断标志：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.info(Thread.currentThread().getName() + ",park()之后中断标志：" + Thread.currentThread().isInterrupted());
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒!");
        });
        t1.setName("t1");
        t1.start();
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        t1.interrupt();
    }
}
