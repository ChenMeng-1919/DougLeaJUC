package lesson35;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:37
 * @description:
 */
public class TimedWaitingState2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                synchronized (TimedWaitingState2.class) {
                    try {
                        //调用wait方法，让线程等待500秒
                        TimedWaitingState2.class.wait(500 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        //模拟休眠1秒，让thread1运行到wait方法处
        TimeUnit.SECONDS.sleep(1);
        System.out.println("thread1.state:" + thread1.getState());
    }
}
