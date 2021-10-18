package lesson07;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 17:01
 * @description:volatile与Java内存模型
 * volatile解决了共享变量在多线程中可见性的问题，可见性是指一个线程对共享变量的修改，对于另一个线程来说是否是可以看到的。
 */
@Slf4j
public class Demo1 {

    //public static boolean flag = true;
    public volatile static boolean flag = true;

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            log.info("线程" + this.getName() + " in");
            while (flag) {
                ;
            }
            log.info("线程" + this.getName() + "停止了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();
        //休眠1秒
        Thread.sleep(1000);
        //将flag置为false
        flag = false;
    }
}
