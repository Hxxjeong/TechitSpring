package annotation;

import java.lang.reflect.Method;

public class ServiceRun {
    public static void main(String[] args) throws Exception{
        Method[] declaredMethod = Service.class.getDeclaredMethods();

        Service service = new Service();

        for(Method m: declaredMethod) {
            if(m.isAnnotationPresent(PrintAnnotation.class)) {
                System.out.println("[[[[[" + m.getName() + "]]]]]");

                PrintAnnotation annotation = m.getAnnotation(PrintAnnotation.class);
                for(int i=0; i<annotation.number(); i++) {
                    System.out.print(annotation.value());
                }
                System.out.println();
            }

            // 사용자가 구현한 메소드 실행
            m.invoke(service);
        }
    }
}
