package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
 * @author: cm
 * @date: Created in 2021/11/4 18:11
 * @description:
 */
@Slf4j
public class Demo7 {

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
        private String name;

        private C1() {
            log.info("C1 default constructor!");
        }

        private C1(String name) {
            this.name = name;
            log.info("C1 有参 constructor!");
        }
    }

    public static void main(String[] args) throws InstantiationException {
        log.info("{}", unsafe.allocateInstance(C1.class));
    }
}
