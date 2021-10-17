package lesson06;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/15 9:59
 * @description:终止线程
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                log.info("start");
                System.out.println("1111");
                boolean flag = true;
                while (flag) {
                }
                log.info("end");
            }
        };
        thread1.setName("thread1");
        thread1.start();
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //关闭线程thread1
        thread1.stop();
        //输出线程thread1的状态
        log.info("{}", thread1.getState());
        //当前线程休眠1秒
        TimeUnit.SECONDS.sleep(1);
        //输出线程thread1的状态
        log.info("{}", thread1.getState());
    }
}
