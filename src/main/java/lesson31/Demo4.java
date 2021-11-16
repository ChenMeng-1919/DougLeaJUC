package lesson31;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 10:18
 * @description:方式4：FutureTask方式1
 */
@Slf4j
public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("{}", System.currentTimeMillis());
        //创建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //将futureTask传递一个线程运行
        new Thread(futureTask).start();
        log.info("{}", System.currentTimeMillis());
        //futureTask.get()会阻塞当前线程，直到futureTask执行完毕
        Integer result = futureTask.get();
        log.info(System.currentTimeMillis() + ":" + result);
    }
}
