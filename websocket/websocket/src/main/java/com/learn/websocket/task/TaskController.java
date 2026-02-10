package com.learn.websocket.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/save")
    public Object create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/update/{id}")
    public Object update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/getAll")
    public Object getAll() {
        return taskService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
