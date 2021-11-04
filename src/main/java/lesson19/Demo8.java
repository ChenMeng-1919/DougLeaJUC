package lesson19;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/4 14:41
 * @description:Future其他方法介绍一下
 */
@Slf4j
public class Demo8 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            TimeUnit.SECONDS.sleep(20);
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end!");

            return 10;
        });
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);
        result.cancel(false);
        log.info("{}", result.isCancelled());
        log.info("{}", result.isDone());
        TimeUnit.SECONDS.sleep(5);
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());
        executorService.shutdown();
    }
}
