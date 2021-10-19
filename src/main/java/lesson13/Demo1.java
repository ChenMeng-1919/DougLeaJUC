package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/10/19 11:24
 * @description:synchronized中等待和唤醒线程示例
 * 线程t1先获取锁，然后调用了wait()方法将线程置为等待状态，然后会释放lock的锁
 * 主线程等待5秒之后，启动线程t2，t2获取到了锁，结果中1、3行时间相差5秒左右
 * t2调用lock.notify()方法，准备将等待在lock上的线程t1唤醒，notify()方法之后又休眠了5秒，
 * 看一下输出的5、8可知，notify()方法之后，t1并不能立即被唤醒，
 * 需要等到t2将synchronized块执行完毕，释放锁之后，t1才被唤醒
 * wait()方法和notify()方法必须放在同步块内调用（synchronized块内），否则会报错
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
