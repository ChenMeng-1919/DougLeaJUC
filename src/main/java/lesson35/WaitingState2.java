package lesson35;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:33
 * @description:
 */
@Slf4j
public class WaitingState2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                while (true) ;
            }
        };
        thread1.start();
        //join方法会让当前主线程等待thread1结束
        thread1.join();
    }
}
