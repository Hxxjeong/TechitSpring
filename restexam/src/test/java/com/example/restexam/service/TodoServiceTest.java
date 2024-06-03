package com.example.restexam.service;

import com.example.restexam.domain.Todo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {
    @Autowired TodoService todoService;
    private static final Logger log = LoggerFactory.getLogger(TodoServiceTest.class);

    @Test
    void getTodos() {
        List<Todo> todos = todoService.getTodos();
        todos.forEach(t -> log.info("todo: {}, done: {}", t.getTodo(), t.isDone()));
    }

    @Test
    void getTodo() {
        Todo todo = todoService.getTodo(1L);
        log.info("todo: {}", todo);
    }

    @Test
    void createTodo() {
        Todo todo1 = todoService.createTodo("todo1");
        log.info("todo: {}", todo1.getTodo());
    }

    @Test
    void updateTodoById() {
        log.info("Before todo :::: {}", todoService.getTodo(1L));
        Todo todo = todoService.updateTodo(1L);
        log.info("After todo :::: {}", todo);

    }

    @Test
    void updateTodo() {
        Todo todo = new Todo("update todo");
        todo.setId(1L);
        log.info("Before todo :::: {}", todoService.getTodo(1L));
        todoService.updateTodo(todo);
        log.info("After todo :::: {}", todoService.getTodo(1L));
    }

    @Test
    void deleteTodo() {
        todoService.deleteTodo(1L);
        log.info("todo ::: {}", todoService.getTodo(1L));
    }
}