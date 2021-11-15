package lesson29;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/*
 * @author: cm
 * @date: Created in 2021/11/15 18:43
 * @description:
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5);//设置QPS为5
        for (int i = 0; i < 10; i++) {
            rateLimiter.acquire();
            log.info("{}", System.currentTimeMillis());
        }
        log.info("----------");
        //可以随时调整速率，我们将qps调整为10
        rateLimiter.setRate(10);
        for (int i = 0; i < 10; i++) {
            rateLimiter.acquire();
            log.info("{}", System.currentTimeMillis());
        }
    }
}
