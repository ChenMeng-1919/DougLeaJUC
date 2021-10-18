package lesson06;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 16:41
 * @description:
 */
@Slf4j
public class Demo8 {

    static int num = 0;

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            log.info(System.currentTimeMillis() + ",start " + this.getName());
            for (int i = 0; i < 10; i++) {
                num++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e
                ) {
                    e.printStackTrace();
                }
            }
            log.info(System.currentTimeMillis() + ",end " + this.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        t1.start();
        t1.join();
        log.info(System.currentTimeMillis() + ",num = " + num);
    }
}
