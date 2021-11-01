package lesson17;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/*
 * @author: cm
 * @date: Created in 2021/11/1 14:52
 * @description:
 */
@Slf4j
public class Demo2 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        //等待吃饭
        void eat() {
            try {
                //模拟休眠
                TimeUnit.SECONDS.sleep(sleep);
                long starTime = System.currentTimeMillis();
                //调用await()的时候，当前线程将会被阻塞，需要等待其他员工都到达await了才能继续
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                log.info(this.getName() + ",sleep:" + this.sleep + " 等待了" + (endTime - starTime) + "(ms),开始吃饭了！");
                //休眠sleep时间，模拟当前员工吃饭耗时
                TimeUnit.SECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        //等待所有人到齐之后，开车去下一站
        void drive() {
            try {
                long starTime = System.currentTimeMillis();
                //调用await()的时候，当前线程将会被阻塞，需要等待其他员工都到达await了才能继续
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                log.info(this.getName() + ",sleep:" + this.sleep + " 等待了" + (endTime - starTime) + "(ms),去下一景点的路上！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            //等待所有人到齐之后吃饭，先到的人坐那等着，什么事情不要干
            this.eat();
            //等待所有人到齐之后开车去下一景点，先到的人坐那等着，什么事情不要干
            this.drive();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            new T("员工" + i, i).start();
        }
    }
}
