package com.cookie.todolist.service;

import com.cookie.todolist.vo.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService {

    Task addTask(Task task);

    Task getTask(int id) throws Exception;

    List<Task> listTasks();

    Boolean deleteTasks(List<Integer> ids);

    Task modifyTask(int id,Task task);
}
