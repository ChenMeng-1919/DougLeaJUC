package lesson19;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/4 14:33
 * @description:获取异步任务执行结果
 */
@Slf4j
public class Demo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            TimeUnit.SECONDS.sleep(5);
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end!");
            return 10;
        });
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());
    }
}
