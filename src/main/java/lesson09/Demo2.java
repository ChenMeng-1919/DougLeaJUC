package lesson09;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 20:41
 * @description:当程序中所有的用户线程执行完毕之后，不管守护线程是否结束，系统都会自动退出。
 */
@Slf4j
public class Demo2 {

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
        t1.setDaemon(true);
        t1.start();
        log.info("主线程结束");
    }
}
