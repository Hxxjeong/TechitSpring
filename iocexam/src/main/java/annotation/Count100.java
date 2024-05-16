package annotation;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME) // annotation 실행 시기
public @interface Count100 {
}
