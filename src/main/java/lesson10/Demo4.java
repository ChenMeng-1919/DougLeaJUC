package lesson10;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 21:19
 * @description:除了使用关键字修饰实例方法和静态方法外，还可以使用同步代码块，
 * 在某些情况下，我们编写的方法体可能比较大，同时存在一些比较耗时的操作，
 * 而需要同步的代码又只有一小部分，如果直接对整个方法进行同步操作，可能会得不偿失，
 * 此时我们可以使用同步代码块的方式对需要同步的代码进行包裹，这样就无需对整个方法进行同步操作了，
 * 同步代码块的使用示例如下：
 */
@Slf4j
public class Demo4 implements Runnable {

    static Demo4 instance = new Demo4();

    static int i = 0;

    @Override
    public void run() {
        //省略其他耗时操作....
        //使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (instance) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info("{}", i);
    }
}
