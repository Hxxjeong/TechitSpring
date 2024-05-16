package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // 메소드에만 사용 가능
public @interface PrintAnnotation {
    String value() default "*";

    int number() default 5;
}
