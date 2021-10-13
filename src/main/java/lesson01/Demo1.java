package lesson01;

/*
 * @author: cm
 * @date: Created in 2021/10/13 21:57
 * @description:死锁
 */
public class Demo1 {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(new SynAddRunalbe(obj1, obj2, 1, 2, true));
        thread1.setName("thread1");
        thread1.start();
        Thread thread2 = new Thread(new SynAddRunalbe(obj1, obj2, 1, 2, false));
        thread1.setName("thread2");
        thread1.start();
    }

    public static class SynAddRunalbe implements Runnable {
        Object obj1;
        Object obj2;
        int a, b;
        boolean flag;

        public SynAddRunalbe(Object obj1, Object obj2, int a, int b, boolean flag) {
            this.obj1 = obj1;
            this.obj2 = obj2;
            this.a = a;
            this.b = b;
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                if (flag) {
                    synchronized (obj1) {
                        Thread.sleep(100);
                        synchronized (obj2) {
                            System.out.println(a + b);
                        }
                    }
                } else {
                    synchronized (obj2) {
                        Thread.sleep(100);
                        synchronized (obj1) {
                            System.out.println(a + b);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Obj1 {
    }

    public static class Obj2 {

    }
}
