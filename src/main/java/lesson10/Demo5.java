package lesson10;

/*
 * @author: cm
 * @date: Created in 2021/10/18 21:28
 * @description:
 */
public class Demo5 {

    //作用于当前类的实例对象
    public synchronized void m1() {
    }

    //作用于当前类的实例对象
    public synchronized void m2() {
    }

    //作用于当前类的实例对象
    public void m3() {
        synchronized (this) {
        }
    }

    //作用于当前类Class对象
    public static synchronized void m4() {
    }

    //作用于当前类Class对象
    public static void m5() {
        synchronized (Demo5.class) {
        }
    }

    public static class T extends Thread {

        Demo5 demo5;

        public T(Demo5 demo5) {
            this.demo5 = demo5;
        }

        @Override
        public void run() {
            super.run();
        }
    }

    public static void main(String[] args) {
        Demo5 d1 = new Demo5();
        Thread t1 = new Thread(() -> {
            d1.m1();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            d1.m2();
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            d1.m2();
        });
        t3.start();

        Demo5 d2 = new Demo5();
        Thread t4 = new Thread(() -> {
            d2.m2();
        });
        t4.start();

        Thread t5 = new Thread(() -> {
            Demo5.m4();
        });
        t5.start();

        Thread t6 = new Thread(() -> {
            Demo5.m5();
        });
        t6.start();
    }
}
