package lesson10;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 21:01
 * @description:synchronized主要有3种使用方式
    修饰实例方法，作用于当前实例，进入同步代码前需要先获取实例的锁

    修饰静态方法，作用于类的Class对象，进入修饰的静态方法前需要先获取类的Class对象的锁

    修饰代码块，需要指定加锁对象(记做lockobj)，在进入同步代码块前需要先获取lockobj的锁
 */
@Slf4j
public class Demo1 {
    static int num = 0;

    /*public static void m1() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }*/
    public static synchronized void m1() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            Demo1.m1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        T1 t3 = new T1();
        t1.start();
        t2.start();
        t3.start();
        //等待3个线程结束打印num
        t1.join();
        t2.join();
        t3.join();
        log.info("{}", Demo1.num);
        /**
         * 打印结果：
         * 25572
         */
    }
}
