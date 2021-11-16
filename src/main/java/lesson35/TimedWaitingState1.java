package lesson35;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:35
 * @description:
 */
@Slf4j
public class TimedWaitingState1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                //休眠500秒 = 500000毫秒
                try {
                    Thread.sleep(500 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        //模拟休眠1秒，让thread1运行到sleep方法处
        TimeUnit.SECONDS.sleep(1);
        log.info("thread1.state:" + thread1.getState());
    }
}
