package lesson12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 14:52
 * @description:获取锁限时等待的方法 tryLock()，
 * 可以选择传入时间参数，表示等待指定的时间，
 * 无参则表示立即返回锁申请的结果：true表示获取锁成功，false表示获取锁失败。
 */
@Slf4j
public class Demo6 {

    private static ReentrantLock lock1 = new ReentrantLock(false);

    public static class T extends Thread {

        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                log.info(System.currentTimeMillis() + ":" + this.getName() + "开始获取锁!");
                //获取锁超时时间设置为3秒，3秒内是否能否获取锁都会返回
                if (lock1.tryLock()) {
                    log.info(System.currentTimeMillis() + ":" + this.getName() + "获取到了锁!");
                    //获取到锁之后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    log.info(System.currentTimeMillis() + ":" + this.getName() + "未能获取到锁!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    log.info(System.currentTimeMillis() + ":" + this.getName() + "释放锁");
                    lock1.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        T t1 = new T("t1");
        T t2 = new T("t2");
        t1.start();
        t2.start();
    }
}
