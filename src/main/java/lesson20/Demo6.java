package lesson20;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/4 16:29
 * @description:
 */
@Slf4j
public class Demo6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            String taskName = "任务" + i;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                log.info(taskName + "执行完毕!");
                return j;
            });
        }
        Integer integer = executorService.invokeAny(list);
        log.info("耗时:" + (System.currentTimeMillis() - startime) + ",执行结果:" + integer);
        executorService.shutdown();
    }
}
