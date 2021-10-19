package lesson11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 10:57
 * @description:当线程处于阻塞状态时，调用线程的interrupt()实例方法，
 * 线程内部会触发InterruptedException异常，并且会清除线程内部的中断标志（即将中断标志置为false）
 * main方法中调用了t.interrupt()方法，此时线程t内部的中断标志会置为true
 * 然后会触发run()方法内部的InterruptedException异常，所以运行结果中有异常输出，
 * 上面说了，当触发InterruptedException异常时候，
 * 线程内部的中断标志又会被清除（变为false），所以在catch中又调用了this.interrupt()一次，
 * 将中断标志置为false
 *run()方法中通过this.isInterrupted()来获取线程的中断标志，退出循环（break）
 */
@Slf4j
public class Demo4 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                log.info("业务处理中");
                //下面模拟阻塞代码
                try {
                    log.info("睡10s");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("异常处理");
                    this.interrupt();
                }
                if (this.isInterrupted()) {
                    log.info("即将退出业务");
                    break;
                }
            }
            log.info("退出业务");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
    }
    /*当一个线程处于被阻塞状态或者试图执行一个阻塞操作时，
    可以使用 Thread.interrupt()方式中断该线程，
    注意此时将会抛出一个InterruptedException的异常，
    同时中断状态将会被复位(由中断状态改为非中断状态)
    内部有循环体，可以通过一个变量来作为一个信号控制线程是否中断，
    注意变量需要volatile修饰 文中的几种方式可以结合起来灵活使用控制线程的中断*/
}
