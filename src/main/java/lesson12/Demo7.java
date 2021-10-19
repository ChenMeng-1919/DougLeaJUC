package lesson12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 15:08
 * @description:
 * 关于tryLock()方法和tryLock(long timeout, TimeUnit unit)方法，说明一下：

    都会返回boolean值，结果表示获取锁是否成功

    tryLock()方法，不管是否获取成功，都会立即返回；
    * 而有参的tryLock方法会尝试在指定的时间内去获取锁，中间会阻塞的现象，
    * 在指定的时间之后会不管是否能够获取锁都会返回结果

    tryLock()方法不会响应线程的中断方法；而有参的tryLock方法会响应线程的中断方法，
    * 而出发 InterruptedException异常，这个从2个方法的声明上可以可以看出来

    ReentrantLock其他常用的方法
    isHeldByCurrentThread：实例方法，判断当前线程是否持有ReentrantLock的锁，上面代码中有使用过。
 */
@Slf4j
public class Demo7 {

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
                if (lock1.tryLock(3, TimeUnit.SECONDS)) {
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
