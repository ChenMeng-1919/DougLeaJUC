package lesson35;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:34
 * @description:
 */
@Slf4j
public class WaitingState3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                LockSupport.park();
            }
        };
        thread1.start();
        //模拟休眠1秒，让thread1运行到park方法处
        TimeUnit.SECONDS.sleep(1);
        log.info("thread1.state:" + thread1.getState());
    }
}
