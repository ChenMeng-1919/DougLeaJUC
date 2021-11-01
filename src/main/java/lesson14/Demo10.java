package lesson14;

import java.util.concurrent.locks.LockSupport;

/*
 * @author: cm
 * @date: Created in 2021/11/1 11:24
 * @description:
 */
public class Demo10 {
    static class BlockerDemo {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            LockSupport.park(new BlockerDemo());
        });
        t2.setName("t2");
        t2.start();
    }
}
