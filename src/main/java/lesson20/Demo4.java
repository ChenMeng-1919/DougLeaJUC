package lesson20;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/*
 * @author: cm
 * @date: Created in 2021/11/4 16:18
 * @description:执行一批任务，然后消费执行结果
 */
@Slf4j
public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                return j;
            });
        }
        solve(executorService, list, a -> {
            log.info(System.currentTimeMillis() + ":" + a);
        });
        executorService.shutdown();
    }

    public static <T> void solve(Executor e, Collection<Callable<T>> solvers, Consumer<T> use) throws InterruptedException, ExecutionException {
        CompletionService<T> ecs = new ExecutorCompletionService<T>(e);
        for (Callable<T> s : solvers) {
            ecs.submit(s);
        }
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            T r = ecs.take().get();
            if (r != null) {
                use.accept(r);
            }
        }
    }
}
