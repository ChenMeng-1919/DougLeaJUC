package lesson08;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 17:21
 * @description:创建线程关联线程组
 */
@Slf4j
public class Demo1 {

    public static class R1 implements Runnable {
        @Override
        public void run() {
            log.info("threadName:" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e
            ) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup, new R1(), "t1");
        Thread t2 = new Thread(threadGroup, new R1(), "t2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("活动线程数:" + threadGroup.activeCount());
        log.info("活动线程组:" + threadGroup.activeGroupCount());
        log.info("线程组名称:" + threadGroup.getName());
    }
}
