package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.MyBean;
import sample.config.MyBeanConfig;

public class SpringExam {
    public static void main(String[] args) {
        // 직접 인스턴스를 만들어 사용하는 경우
//        MyBean bean = new MyBean();
//        bean.setName("kim");
//
//        System.out.println(bean);

        // Spring IoC Container를 이용해서 사용하는 경우
        // 1. 어노테이션으로 컨테이너에게 알리기 (컴포넌트 스캔 필요)

        // 2. Java Config로 알리기
        // BeanFactory (간단한 기능만 포함한 빈 생성 / AOP 사용 X)
        // ApplicationContext

        // 공장이 생성될 때 어떤 Bean을 생성해야 할지 알아야 함
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);    // MyBean 객체가 만들어짐
        MyBean bean1 = (MyBean) context.getBean("myBean");// myBean으로 된 메소드를 가져옴 (id를 알려줌) (Dependency Lookup)

        bean1.setName("choi");
        System.out.println(bean1);
        
//        MyBean bean2 = context.getBean(MyBean.class);   // 타입만 알려줌
        MyBean bean2 = context.getBean("myBean", MyBean.class);
        MyBean bean3 = context.getBean("myBean2", MyBean.class);   // id, 타입을 알려줌
        bean2.setName("park");
        System.out.println(bean2);
        System.out.println(bean1);  // name이 park이 됨 (같은 인스턴스기 때문: Singleton)

        if(bean1 == bean2) System.out.println("same b1 b2");
        if(bean1 == bean3) System.out.println("same b1 b3");
        else System.out.println("different b1 b3");

        MyBean bean4 = context.getBean("myBean3", MyBean.class);
        bean4.setName("lee");
        System.out.println(bean4);

        MyBean bean5 = context.getBean("myBean3", MyBean.class);
        bean5.setName("jung");
        System.out.println(bean5);

        // 3. XML로 알리기
    }
}
