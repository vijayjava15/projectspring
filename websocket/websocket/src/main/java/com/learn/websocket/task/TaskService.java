package com.learn.websocket.task;

public interface TaskService {

    Object create(Task task);

    Object update(Long id, Task task);

    Object getById(Long id);

    Object getAll();

    Object delete(Long id);
}
