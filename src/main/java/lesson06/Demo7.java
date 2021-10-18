package lesson06;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 14:55
 * @description:挂起（suspend）和继续执行（resume）线程
 */
@Slf4j
public class Demo7 {

    static Object object = new Object();

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                log.info("in " + this.getName());
                Thread.currentThread().suspend();
                log.info("{} end",this.getName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        t1.start();
        Thread.sleep(1000);
        T1 t2 = new T1("t2");
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
        log.info("main end");
    }
}
