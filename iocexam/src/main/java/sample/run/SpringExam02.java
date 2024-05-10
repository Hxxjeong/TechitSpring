package sample.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.Book;
import sample.config.BookConfig;

public class SpringExam02 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookConfig.class);
//        Book book = context.getBean(Book.class);
        Book book = context.getBean("b", Book.class);   // 컴포넌트 이름 설정

        book.setTitle("java");
        book.setPrice(15000);
        System.out.println(book);
    }
}
