package lesson09;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/18 20:47
 * @description:父线程如果为用户线程，子线程默认也是用户现场，父线程如果是守护线程，子线程默认也是守护线程。
 */
@Slf4j
public class Demo4 {
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            log.info(this.getName() + ".daemon:" + this.isDaemon());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        log.info(Thread.currentThread().getName() + ".daemon:" + Thread.currentThread().isDaemon());
        T1 t1 = new T1("t1");
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                log.info(this.getName() + ".daemon:" + this.isDaemon());
                T1 t3 = new T1("t3");
                t3.start();
            }
        };
        t2.setName("t2");
        t2.setDaemon(true);
        t2.start();
        TimeUnit.SECONDS.sleep(2);
    }
}
