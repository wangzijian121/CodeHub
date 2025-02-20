

package java.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 注释为@Deprecated的程序元素是不鼓励程序员使用的，通常是因为它很危险，或者因为存在更好的替代方案。
 * 当在非弃用代码中使用或覆盖弃用的程序元素时，编译器会发出警告。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}
