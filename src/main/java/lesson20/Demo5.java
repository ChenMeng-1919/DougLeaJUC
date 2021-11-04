package lesson20;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/4 16:25
 * @description:异步执行一批任务，有一个完成立即返回，其他取消
 */
@Slf4j
public class Demo5 {
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
        Integer integer = invokeAny(executorService, list);
        log.info("耗时:" + (System.currentTimeMillis() - startime) + ",执行结果:" + integer);
        executorService.shutdown();
    }

    public static <T> T invokeAny(Executor e, Collection<Callable<T>> solvers) throws InterruptedException, ExecutionException {
        CompletionService<T> ecs = new ExecutorCompletionService<T>(e);
        List<Future<T>> futureList = new ArrayList<>();
        for (Callable<T> s : solvers) {
            futureList.add(ecs.submit(s));
        }
        int n = solvers.size();
        try {
            for (int i = 0; i < n; ++i) {
                T r = ecs.take().get();
                if (r != null) {
                    return r;
                }
            }
        } finally {
            for (Future<T> future : futureList) {
                future.cancel(true);
            }
        }
        return null;
    }
}
