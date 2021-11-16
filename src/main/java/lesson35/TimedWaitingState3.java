package lesson35;

/*
 * @author: cm
 * @date: Created in 2021/11/16 15:01
 * @description:
 */
public class TimedWaitingState3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                while (true) ;
            }
        };
        thread1.start();
        //join方法会让当前主线程等待thread1结束，最长等待500s，如果500s
        thread1.join(500 * 1000);
    }
}
