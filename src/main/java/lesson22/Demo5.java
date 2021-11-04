package lesson22;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
 * @author: cm
 * @date: Created in 2021/11/4 18:08
 * @description:
 */
@Slf4j
public class Demo5 {
    static Unsafe unsafe;
    //静态属性
    private static Object v1;
    //实例属性
    private Object v2;

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

    public static void main(String[] args) throws NoSuchFieldException {
        Field v1Field = Demo5.class.getDeclaredField("v1");
        Field v2Field = Demo5.class.getDeclaredField("v2");
        log.info("{}", unsafe.staticFieldOffset(v1Field));
        log.info("{}", unsafe.objectFieldOffset(v2Field));
        log.info("{}", unsafe.staticFieldBase(v1Field) == Demo5.class);
    }
}
