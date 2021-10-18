package lesson09;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 20:37
 * @description:用户线程与守护线程
 */
@Slf4j
public class Demo1 {
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            log.info(this.getName() + "开始执行," + (this.isDaemon() ? "我是守护线程" : "我是用户线程"));
            while (true)
                ;
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1("子线程1");
        t1.start();
        log.info("主线程结束");
    }
}
