package lesson35;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:31
 * @description:
 */
@Slf4j
public class WaitingState1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                synchronized (WaitingState1.class) {
                    try {
                        //调用wait方法，让线程等待
                        WaitingState1.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        //模拟休眠1秒，让thread1运行到wait方法处
        TimeUnit.SECONDS.sleep(1);
        System.out.println("thread.state:" + thread1.getState());
    }
}
