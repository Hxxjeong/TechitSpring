package annotation;

import java.lang.reflect.Method;

public class HelloRun {
    public static void main(String[] args) throws Exception {
        Hello hello = new Hello();
        Method method = hello.getClass().getDeclaredMethod("print");

        // 메소드에 어노테이션이 있는지 확인
        if (method.isAnnotationPresent(Count100.class)) {
            for(int i=0; i<100; i++) {
                hello.print();
            }
        }
        else
            hello.print();
    }
}
