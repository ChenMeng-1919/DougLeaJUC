package lesson08;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 18:19
 * @description:批量停止线程
 */
@Slf4j
public class Demo4 {
    public static class R1 implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            log.info("所属线程组:" + thread.getThreadGroup().getName() + ",线程名称:" + thread.getName());
            while (!thread.isInterrupted()) {
                ;
            }
            log.info("线程:" + thread.getName() + "停止了！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup1 = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup1, new R1(), "t1");
        Thread t2 = new Thread(threadGroup1, new R1(), "t2");
        t1.start();
        t2.start();
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "thread-group-2");
        Thread t3 = new Thread(threadGroup2, new R1(), "t3");
        Thread t4 = new Thread(threadGroup2, new R1(), "t4");
        t3.start();
        t4.start();
        TimeUnit.SECONDS.sleep(1);
        log.info("-----------threadGroup1信息-----------");
        threadGroup1.list();
        log.info("----------------------");
        log.info("停止线程组：" + threadGroup1.getName() + "中的所有子孙线程");
        threadGroup1.interrupt();
        TimeUnit.SECONDS.sleep(2);
        log.info("----------threadGroup1停止后，输出信息------------");
        threadGroup1.list();
    }
}
