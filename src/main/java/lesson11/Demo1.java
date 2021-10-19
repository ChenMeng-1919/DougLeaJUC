package lesson11;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 10:35
 * @description:通过一个变量控制线程中断
 */
@Slf4j
public class Demo1 {

    public volatile static boolean exit = false;

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                log.info("业务处理中");
                if (exit) {
                    log.info("准备退出业务处理");
                    break;
                }
            }
            log.info("退出业务处理");
        }
    }

    public static void setExit() {
        exit = true;
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        setExit();
    }
}
