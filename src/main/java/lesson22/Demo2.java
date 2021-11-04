package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/4 18:02
 * @description:
 */
@Slf4j
public class Demo2 {
    static Unsafe unsafe;
    //用来记录网站访问量，每次访问+1
    static int count;
    //count在Demo.class对象中的地址偏移量
    static long countOffset;

    static {
        try {
            //获取Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Field countField = Demo2.class.getDeclaredField("count");
            //获取count字段在Demo2中的内存地址的偏移量
            countOffset = unsafe.staticFieldOffset(countField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //模拟访问一次
    public static void request() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        //对count原子加1
        unsafe.getAndAddInt(Demo2.class, countOffset, 1);
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
