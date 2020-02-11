package com.victor.todo.service;

import com.victor.todo.model.Todo;

import java.util.List;

public interface TodoService {


    Todo create(Todo todo);


    List<Todo> getAllTodos();

    Todo findById(Long id);

    Todo update(Todo todo);

    void delete(Long id);

    Todo findATask(Long id);
}
