package annotation;

public class Service {
    @PrintAnnotation    // Annotation에 default 값이 없으면 에러
    public void method01() {
        System.out.println("method 01 run");
    }

    @PrintAnnotation("&")
    public void method02() {
        System.out.println("method 02 run");
    }

    @PrintAnnotation(value = "#", number = 9)
    public void method03() {
        System.out.println("method 03 run");
    }

    public void method04() {
        System.out.println("method 04 run");
    }
}
