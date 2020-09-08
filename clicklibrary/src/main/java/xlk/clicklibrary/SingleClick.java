package xlk.clicklibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author xlk
 * @date 2020/8/31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleClick {
    //限制时长（单位毫秒）
    int value() default 1000;

    //方法中需要限制的按钮
    int[] specify() default {};

    //方法中需要取消限制的按钮
    int[] except() default {};
}
