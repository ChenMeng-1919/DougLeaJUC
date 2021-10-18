package lesson10;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 21:07
 * @description:synchronized作用于实例对象
 * synchronize作用于实例方法需要注意：

实例方法上加synchronized，线程安全的前提是，多个线程操作的是同一个实例，如果多个线程作用于不同的实例，那么线程安全是无法保证的

同一个实例的多个实例方法上有synchronized，这些方法都是互斥的，同一时间只允许一个线程操作同一个实例的其中的一个synchronized方法
 */
@Slf4j
public class Demo2 {

    int num = 0;

    public synchronized void add() {
        num++;
    }

    public static class T extends Thread {

        private Demo2 demo2;

        public T(Demo2 demo2) {
            this.demo2 = demo2;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                this.demo2.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo2 demo2 = new Demo2();
        T t1 = new T(demo2);
        T t2 = new T(demo2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info(String.valueOf(demo2.num));
    }
}
