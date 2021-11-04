package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/*
 * @author: cm
 * @date: Created in 2021/11/4 18:07
 * @description:
 */
@Slf4j
public class Demo4 {

    static Unsafe unsafe;
    //用来记录网站访问量，每次访问+1
    static int count;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //模拟访问一次
    public static void request() {
        unsafe.monitorEnter(Demo4.class);
        try {
            count++;
        } finally {
            unsafe.monitorExit(Demo4.class);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        log.info(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
    }
}
