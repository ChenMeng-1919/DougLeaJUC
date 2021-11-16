package lesson35;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 15:05
 * @description:
 */
@Slf4j
public class TerminatedState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                log.info("{}", Thread.currentThread());
            }
        };
        thread1.start();
        //休眠1秒，等待thread1执行完毕
        TimeUnit.SECONDS.sleep(1);
        log.info("thread1 state:" + thread1.getState());
    }
}
