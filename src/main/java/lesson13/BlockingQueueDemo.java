package lesson13;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author: cm
 * @date: Created in 2021/10/19 18:33
 * @description:同一个锁支持创建多个Condition
 *   使用两个Condition来实现一个阻塞队列的例子：
 *
 * 使用condition的步骤：创建condition对象，获取锁，然后调用condition的方法

1、一个ReentrantLock支持床多个condition对象

2、voidawait()throwsInterruptedException;方法会释放锁，让当前线程等待，支持唤醒，支持线程中断

3、voidawaitUninterruptibly();方法会释放锁，让当前线程等待，支持唤醒，不支持线程中断

4、longawaitNanos(longnanosTimeout)throwsInterruptedException;参数为纳秒，此方法会释放锁，让当前线程等待，支持唤醒，支持中断。超时之后返回的，结果为负数；超时之前被唤醒返回的，结果为正数（表示返回时距离超时时间相差的纳秒数）

5、booleanawait(longtime,TimeUnitunit)throwsInterruptedException;方法会释放锁，让当前线程等待，支持唤醒，支持中断。超时之后返回的，结果为false；超时之前被唤醒返回的，结果为true

6、booleanawaitUntil(Datedeadline)throwsInterruptedException;参数表示超时的截止时间点，方法会释放锁，让当前线程等待，支持唤醒，支持中断。超时之后返回的，结果为false；超时之前被唤醒返回的，结果为true

7、voidsignal();会唤醒一个等待中的线程，然后被唤醒的线程会被加入同步队列，去尝试获取锁

8、voidsignalAll();会唤醒所有等待中的线程，将所有等待中的线程加入同步队列，然后去尝试获取锁
 */
@Slf4j
public class BlockingQueueDemo<E> {
    //阻塞队列最大容量
    int size;

    ReentrantLock lock = new ReentrantLock();
    //队列底层实现
    LinkedList<E> list = new LinkedList<>();
    //队列满时的等待条件
    Condition notFull = lock.newCondition();
    //队列空时的等待条件
    Condition notEmpty = lock.newCondition();

    public BlockingQueueDemo(int size) {
        this.size = size;
    }

    //入队方法
    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size)
                //队列已满,在notFull条件上等待
                notFull.await();
            //入队:加入链表末尾
            list.add(e);
            log.info("入队：" + e);
            //通知在notEmpty条件上等待的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    //出队方法
    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0)
                //队列为空,在notEmpty条件上等待
                notEmpty.await();
            //出队:移除链表首元素
            e = list.removeFirst();
            log.info("出队：" + e);
            //通知在notFull条件上等待的线程
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(2);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enqueue(data);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer data = queue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
