package lesson04;

/*
 * @author: cm
 * @date: Created in 2021/10/14 11:26
 * @description:在单例模式的实现上有一种双重检验锁定的方式
 * 我们先看 instance=newSingleton();

    未被编译器优化的操作：

    指令1：分配一款内存M

    指令2：在内存M上初始化Singleton对象

    指令3：将M的地址赋值给instance变量

    编译器优化后的操作指令：

    指令1：分配一块内存S

    指令2：将M的地址赋值给instance变量

    指令3：在内存M上初始化Singleton对象
 */
public class Singleton {
    static Singleton instance;

    static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
