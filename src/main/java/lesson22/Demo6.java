package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
 * @author: cm
 * @date: Created in 2021/11/4 18:10
 * @description:
 */
@Slf4j
public class Demo6 {

    static Unsafe unsafe;

    static {
        //获取Unsafe对象
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class C1 {
        private static int count;

        static {
            count = 10;
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，C1 static init.");
        }
    }

    static class C2 {
        private static int count;

        static {
            count = 11;
            log.info(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "，C2 static init.");
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        //判断C1类是需要需要初始化，如果已经初始化了，会返回false，如果此类没有被初始化过，返回true
        if (unsafe.shouldBeInitialized(C1.class)) {
            log.info("C1需要进行初始化");
            //对C1进行初始化
            unsafe.ensureClassInitialized(C1.class);
        }
        log.info("{}", C2.count);
        log.info("{}", unsafe.shouldBeInitialized(C1.class));
    }
}
