package com.example.restexam.service;

import com.example.restexam.domain.Todo;
import com.example.restexam.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;

    // 전체 리스트
    public List<Todo> getTodos() {
        return todoRepository.findAll();
        // 정렬하는 경우
//        return todoRepository.findAll(Sort.by("id").descending());
    }

    // todo 1개
    public Todo getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 id가 없습니다."));
        return todo;
    }

    // create
    @Transactional
    public Todo createTodo(String todo) {
        Todo newTodo = todoRepository.save(new Todo(todo));
        return newTodo;
    }

    // update (id로 받기)
    // id에 해당하는 done toggle
    @Transactional
    public Todo updateTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 id가 없습니다."));
        todo.setDone(!todo.isDone());
        return todo;    // findById로 찾았기 때문에 영속성 컨텍스트가 관리하게 되여 save를 사용하지 않아도 ok
    }

    // update (todo로 받기)
    // id에 해당하는 todo 정보 수정
    @Transactional
    public Todo updateTodo(Todo todo) {
        Todo todo1 = null;
        try {
            todo1 = todoRepository.save(todo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return todo1;
    }

    // delete
    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 id가 없습니다."));
        todoRepository.delete(todo);
    }
}
