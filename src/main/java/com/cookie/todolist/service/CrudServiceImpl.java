package com.cookie.todolist.service;
import com.cookie.todolist.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cookie.todolist.vo.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrudServiceImpl implements CrudService{
    private static final Logger logger = LoggerFactory.getLogger(CrudServiceImpl.class);

    private static List<Task> tasks;
    private static int nextId;

    static {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public Task addTask(Task task){
        logger.info("enter...addTask...task:{}",task);
        task.setId(nextId++);
        tasks.add(task);
        return task;
    };

    public Task getTask(int id) throws Exception {
        logger.info("enter...getTask...id:{}",id);
        Optional<Task> task = Optional.of(new Task());
        task = tasks.stream().filter(t -> t.getId()==id).findFirst();
        Boolean result = task.isPresent();
        if(result){
            logger.info("Found task:{}", StringUtil.toJsonString(task.get()));
            return task.get();
        }else{
            logger.error("No matching name found");
            throw new RuntimeException("No matching name found");
        }
    };

    public List<Task> listTasks(){
        logger.info("enter...listTasks...tasks:{}",tasks);
        return tasks;
    };

    public Boolean deleteTasks(List<Integer> ids){
        logger.info("enter...deleteTasks...ids:{}",ids);
        for (Integer id : ids) {
            if(tasks.stream().anyMatch(task -> task.getId()==id)){
                tasks = tasks.stream()
                        .filter(task -> task.getId() != id)
                        .collect(Collectors.toList());
            }else{
                logger.error("Task with ID " + id + " not found");
                return false;
            }
        }
        logger.info("Tasks deleted successfully");
        return true;
    };

    public Task modifyTask(int id,Task task){
        task.setId(id);
        logger.info("enter...modifyTask...task{}",task);
        tasks.stream()
                .filter(task1 -> task1.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        item -> {
                            int index = tasks.indexOf(item);
                            tasks.set(index, task);
                            logger.info("Task with ID " + id + " updated successfully");
                        },
                        () -> {
                            throw new RuntimeException("Task with ID " + id + " not found");
                        }
                );
        return task;
    };
}
