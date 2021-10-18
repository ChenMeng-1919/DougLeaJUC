package lesson06;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 10:15
 * @description:通过改变开关变量实现线程中断
 */
@Slf4j
public class Demo3 {
    static volatile boolean isStop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                log.info("start");
                while (true) {
                    log.info("正在运行");
                    if (isStop) {
                        log.info("我要退出了!");
                        break;
                    }
                }
                log.info("end");
            }
        };
        thread1.setName("thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        isStop = true;
    }
}
