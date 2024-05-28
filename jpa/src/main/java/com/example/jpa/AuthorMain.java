package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AuthorMain {
    // author id를 찾아 find
    public static void find(Long id) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try {
            // 입력한 id의 작가 이름 찾기
            Author author = entityManager.find(Author.class, id);
            List<Book> books = author.getBooks();

            log.info("Author name: {}", author.getName());
            for (Book book : books) {
                log.info("Book Name: {}", book.getTitle());
            }
        } finally {
            entityManager.close();
        }
    }

    // create
    public static void create() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Author author = new Author("choi");
            Book book1 = new Book("java", author);
            Book book2 = new Book("java", author);
            Book book3 = new Book("java", author);

            author.getBooks().add(book1);
            author.getBooks().add(book2);
            author.getBooks().add(book3);

            entityManager.persist(author);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // book id를 찾아 update
    public static void update(Long id) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Book book = entityManager.find(Book.class, id);
            book.setTitle("spring");

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // author id를 찾아 delete
    public static void delete(Long id) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Author author = entityManager.find(Author.class, id);
            entityManager.remove(author);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
//        create();
        find(3L);
//        update(2L);
//        delete(1L);
    }
}
