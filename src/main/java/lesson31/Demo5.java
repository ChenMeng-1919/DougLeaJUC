package lesson31;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 10:20
 * @description:方式5：FutureTask方式2
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("{}", System.currentTimeMillis());
        //创建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);
        //将futureTask传递一个线程运行
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futureTask.run();
        }).start();
        log.info("{}", System.currentTimeMillis());
        //futureTask.get()会阻塞当前线程，直到futureTask执行完毕
        Integer result = futureTask.get();
        log.info(System.currentTimeMillis() + ":" + result);
    }
}
