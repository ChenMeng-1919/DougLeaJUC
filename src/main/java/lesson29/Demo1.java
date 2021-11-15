package lesson29;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/15 18:28
 * @description:
 */
@Slf4j
public class Demo1 {
    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                    flag = semaphore.tryAcquire(100, TimeUnit.MICROSECONDS);
                    if (flag) {
                        //休眠2秒，模拟下单操作
                        log.info(Thread.currentThread() + "，尝试下单中。。。。。");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        log.info(Thread.currentThread() + "，秒杀失败，请稍微重试！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag) {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
