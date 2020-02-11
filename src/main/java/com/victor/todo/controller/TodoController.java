package com.victor.todo.controller;

import com.victor.todo.model.Todo;
import com.victor.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodoController {

    @Autowired TodoService todoService;
    @GetMapping("/")
    public String todos(Model model){
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
//        model.addAttribute("pending",todos);
        model.addAttribute("todo", new Todo());
        model.addAttribute("Headtitle", "Todo App");
        model.addAttribute("isAdd", true);
        return "view/todos";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes, Model model){
        Todo dbTodo = todoService.create(todo);
        if (dbTodo != null){
            redirectAttributes.addFlashAttribute("successmessage", "Todo is created successfully");
            return "redirect:/";
        }
        model.addAttribute("errormessage", "Todo not created, Please try again");
        model.addAttribute("todo", todo);
        return "view/todos";

    }

    @GetMapping(value = "/getTodo/{id}")
    public String getTodo(@PathVariable Long id, Model model){
        Todo todo = todoService.findById(id);
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        model.addAttribute("todo", todo);
        model.addAttribute("Headtitle", "Edit Todo App");
        model.addAttribute("isAdd", false);
        return "view/todos";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes, Model model){
        Todo dbTodo = todoService.update(todo);
        if (dbTodo != null){
            redirectAttributes.addFlashAttribute("successmessage", "Todo is updated successfully");
            return "redirect:/";
        }else{
            model.addAttribute("errormessage", "Todo not updated, Please try again");
            model.addAttribute("todo", todo);
            return "view/todos";
        }
    }

    @GetMapping(value = "/deleteTodo/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes){
        todoService.delete(id);
        redirectAttributes.addFlashAttribute("successmessage", "employee is deleted successfully");
        return "redirect:/";
    }

    @GetMapping(value = "/viewTodo/{id}")
    public String viewTodo(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model){
        Todo todo = todoService.findById(id);
        if (todo != null){

        }
        redirectAttributes.addFlashAttribute("todoTitle", todo.getTitle());
        return "redirect:/";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewTask(Model model, @PathVariable(name = "id") Long id){
        Todo foundTask = todoService.findATask(id);
        model.addAttribute("todo", foundTask);
        return "view/onetask";
    }
}
