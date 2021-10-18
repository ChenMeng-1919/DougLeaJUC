package lesson08;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/10/18 18:14
 * @description:根线程组
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) {
        log.info(String.valueOf(Thread.currentThread()));
        log.info(String.valueOf(Thread.currentThread().getThreadGroup()));
        log.info(String.valueOf(Thread.currentThread().getThreadGroup().getParent()));
        log.info(String.valueOf(Thread.currentThread().getThreadGroup().getParent().getParent()));
    }
}
