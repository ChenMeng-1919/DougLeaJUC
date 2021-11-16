package lesson32;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

/*
 * @author: cm
 * @date: Created in 2021/11/16 11:17
 * @description:方式2：AtomicLong实现计数器
 */
@Slf4j
public class Demo2 {
    static AtomicLong count = new AtomicLong(0);
    public static void incr() {
        count.incrementAndGet();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            count.set(0);
            m1();
        }
    }
    private static void m1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000000; j++) {
                        incr();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        log.info(String.format("结果：%s,耗时(ms)：%s", count, (t2 - t1)));
    }
}
