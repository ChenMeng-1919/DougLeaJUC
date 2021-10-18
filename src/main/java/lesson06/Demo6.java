package lesson06;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 14:27
 * @description:wait和notify方法必须包含在对应的synchronize语句汇总，无论是wait()方法或者notify()方法都需要首先获取目标独享的一个监视器
 * Object.wait()方法和Thread.sleeep()方法都可以让现场等待若干时间。
 * 除wait()方法可以被唤醒外，另外一个主要的区别就是wait()方法会释放目标对象的锁，
 * 而Thread.sleep()方法不会释放锁。
 * */
@Slf4j
public class Demo6 {

    static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                log.info(System.currentTimeMillis() + ":T1 start!");
                try {
                    log.info(System.currentTimeMillis() + ":T1 wait for object");
                    object.wait();
                } catch (InterruptedException e
                ) {
                    e.printStackTrace();
                }
                log.info(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }


    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                log.info(System.currentTimeMillis() + ":T2 start，notify one thread! ");
                object.notify();
                log.info(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch
                (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1().start();
        new T2().start();
    }
}
