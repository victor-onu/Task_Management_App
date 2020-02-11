package com.victor.todo.service;

import com.victor.todo.model.Todo;
import com.victor.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired private TodoRepository todoRepository;


    @Override
    public  Todo create(Todo todo) {

        todo.setCreatedAt(new Date());

        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()){
            return todo.get();
        }else{
            return null;
        }


    }

    @Override
    public Todo update(Todo todo) {
        todo.setCreatedAt(todo.getCreatedAt());
        todo.setUpdatedAt(new Date());
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);

    }

    @Override
    public Todo findATask(Long id){
       Todo foundTodo = todoRepository.findById(id).orElse(null);
       return foundTodo;
    }
}
