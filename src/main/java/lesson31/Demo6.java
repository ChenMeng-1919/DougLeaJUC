package lesson31;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 10:21
 * @description:方式6：CompletableFuture方式实现
 */
@Slf4j
public class Demo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("{}", System.currentTimeMillis());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        log.info("{}", System.currentTimeMillis());
        //futureTask.get()会阻塞当前线程，直到futureTask执行完毕
        Integer result = completableFuture.get();
        log.info(System.currentTimeMillis() + ":" + result);
    }
}
