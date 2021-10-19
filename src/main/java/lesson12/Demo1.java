package lesson12;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/19 14:11
 * @description:使用3个线程来对一个共享变量++操作，先使用synchronized实现
 */
@Slf4j
public class Demo1 {

    private static int num = 0;

    private static synchronized void add() {
        num++;
    }

    public static class T extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Demo1.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        log.info("{}", Demo1.num);
    }
}
