package lesson19;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/4 14:49
 * @description:
 */
@Slf4j
public class Demo9 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            TimeUnit.SECONDS.sleep(5);
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end!");
            return 10;
        });
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        new Thread(futureTask).start();
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果:" + futureTask.get());
    }
}
