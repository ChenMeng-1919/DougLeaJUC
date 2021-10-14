package lesson04;

/*
 * @author: cm
 * @date: Created in 2021/10/14 11:35
 * @description:静态内部内的方式实现单例模式
 */
public class SingletonDemo {

    private SingletonDemo() {
    }

    private static class SingletonDemoHandler {
        private static SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return SingletonDemoHandler.instance;
    }
}
