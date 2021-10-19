package lesson11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 10:52
 * @description:线程阻塞状态中如何中断
 *
 * 调用线程的interrupt()实例方法，线程的中断标志会被置为true
当线程处于阻塞状态时，调用线程的interrupt()实例方法，线程内部会触发InterruptedException异常，并且会清除线程内部的中断标志（即将中断标志置为false）
 */
@Slf4j
public class Demo3 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                log.info("业务执行中");
                //下面模拟阻塞代码
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
    }
}
