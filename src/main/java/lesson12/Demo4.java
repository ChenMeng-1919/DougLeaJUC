package lesson12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 14:27
 * @description:ReentrantLock公平锁实例
 */
@Slf4j
public class Demo4 {

    private static int num = 0;

    private static ReentrantLock fairLock = new ReentrantLock(true);

    public static class T extends Thread {

        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                fairLock.lock();
                try {
                    log.info(this.getName() + "获得锁!");
                } finally {
                    log.info(this.getName() + "释放锁!");
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
