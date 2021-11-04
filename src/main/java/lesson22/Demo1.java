package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
 * @author: cm
 * @date: Created in 2021/11/4 17:57
 * @description:通过反射获取Unsafe实例
 */
@Slf4j
public class Demo1 {
    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        log.info("{}", unsafe);
    }
}
