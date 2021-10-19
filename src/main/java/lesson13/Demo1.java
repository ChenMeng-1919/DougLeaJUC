package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 11:24
 * @description:synchronized中等待和唤醒线程示例
 */
@Slf4j
public class Demo1 {
    
    static Object lock = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            log.info(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            synchronized (lock) {
                log.info(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            log.info(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            synchronized (lock) {
                log.info(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                lock.notify();
                log.info(System.currentTimeMillis() + "," + this.getName() + " notify!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e
                ) {
                    e.printStackTrace();
                }
                log.info(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
            }
            log.info(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
    }
}
