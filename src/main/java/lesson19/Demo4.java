package lesson19;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * @author: cm
 * @date: Created in 2021/11/4 14:17
 * @description:定时任务有异常
 */
@Slf4j
public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("{}", System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            log.info(Thread.currentThread().getName());
            log.info(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            log.info("{}", 10 / 0);
            log.info(System.currentTimeMillis() + "第" + currCount + "次" + "执行结束");
        }, 1, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(5);
        log.info("{}", scheduledFuture.isCancelled());
        log.info("{}", scheduledFuture.isDone());
    }
}
