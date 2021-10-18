package lesson06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 14:14
 * @description:程序无法终止,sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                //休眠100秒
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        this.interrupt();
                        e.printStackTrace();
                    }
                    if (this.isInterrupted()) {
                        log.info("我要退出了!");
                        break;
                    }
                }
            }
        };
        thread1.start();
        thread1.interrupt();
    }
}
