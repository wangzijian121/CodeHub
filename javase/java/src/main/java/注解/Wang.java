package 注解;

import java.lang.annotation.*;

/**
 * @author zijian Wang
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Wang {

    int id();

    String name();
}
