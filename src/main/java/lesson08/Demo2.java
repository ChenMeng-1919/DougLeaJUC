package lesson08;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 17:28
 * @description:为线程组指定父线程组
 */
@Slf4j
public class Demo2 {

    public static class R1 implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            log.info("所属线程组:" + thread.getThreadGroup().getName() + ",线程名称:" + thread.getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e
            ) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup1 = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup1, new R1(), "t1");
        Thread t2 = new Thread(threadGroup1, new R1(), "t2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("threadGroup1活动线程数:" + threadGroup1.activeCount());
        log.info("threadGroup1活动线程组:" + threadGroup1.activeGroupCount());
        log.info("threadGroup1线程组名称:" + threadGroup1.getName());
        log.info("threadGroup1父线程组名称:" + threadGroup1.getParent().getName());
        log.info("----------------------");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "thread-group-2");
        Thread t3 = new Thread(threadGroup2, new R1(), "t3");
        Thread t4 = new Thread(threadGroup2, new R1(), "t4");
        t3.start();
        t4.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("threadGroup2活动线程数:" + threadGroup2.activeCount());
        log.info("threadGroup2活动线程组:" + threadGroup2.activeGroupCount());
        log.info("threadGroup2线程组名称:" + threadGroup2.getName());
        log.info("threadGroup2父线程组名称:" + threadGroup2.getParent().getName());
        log.info("----------------------");
        log.info("threadGroup1活动线程数:" + threadGroup1.activeCount());
        log.info("threadGroup1活动线程组:" + threadGroup1.activeGroupCount());
        log.info("----------------------");
        threadGroup1.list();
    }
}
