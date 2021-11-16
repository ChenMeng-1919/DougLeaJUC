package lesson31;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/16 10:05
 * @description:方式3：ExecutorService.submit方法实现
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        log.info("{}", System.currentTimeMillis());
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //关闭线程池
        executorService.shutdown();
        log.info("{}", System.currentTimeMillis());
        Integer result = future.get();
        log.info(System.currentTimeMillis() + ":" + result);
    }
}
