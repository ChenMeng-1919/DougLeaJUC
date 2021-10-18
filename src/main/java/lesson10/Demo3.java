package lesson10;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 21:14
 * @description:synchronized作用于静态方法
 */
@Slf4j
public class Demo3 {

    static int num = 0;

    public static synchronized void m1() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            Demo3.m1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        T1 t3 = new T1();
        t1.start();
        t2.start();
        t3.start();
        //等待3个线程结束打印num
        t1.join();
        t2.join();
        t3.join();
        log.info("{}", Demo3.num);
        /**
         * 打印结果：
         * 30000
         */
    }
}
