package lesson35;

import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/11/16 14:25
 * @description:
 */
@Slf4j
public class NewState {
    public static void main(String[] args) {
        Thread thread1 = new Thread("thread1");
        log.info("{}", thread1.getState());
    }
}
