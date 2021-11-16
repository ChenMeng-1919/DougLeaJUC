package lesson35;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:28
 * @description:
 */
@Slf4j
public class BlockedState {
    static String lock = "锁";

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                synchronized (lock) {
                    //死循环导致thread1一直持有lock对象锁
                    while (true) ;
                }
            }
        };
        thread1.start();
        //休眠1秒，让thread1先启动
        TimeUnit.SECONDS.sleep(1);
        Thread thread2 = new Thread("thread2") {
            @Override
            public void run() {
                synchronized (lock) { //@1
                    log.info("thread2");
                }
            }
        };
        thread2.start();
        log.info("thread1.state:" + thread1.getState());
        log.info("thread2.state:" + thread2.getState());
    }
}
