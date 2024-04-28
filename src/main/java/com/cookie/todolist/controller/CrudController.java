package com.cookie.todolist.controller;

import com.cookie.todolist.response.ApiResponse;
import com.cookie.todolist.service.CrudService;
import com.cookie.todolist.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class CrudController {
    @Autowired
    private CrudService crudService;

    @GetMapping(value = "/getTask/{id}")
    public Task getTask(@PathVariable int id) throws Exception {
        return crudService.getTask(id);
    }

    @GetMapping(value = "/listTasks")
    public List<Task> listTasks(){
        return crudService.listTasks();
    }

    @PostMapping(value = "/addTask")
    public Task addTask(@Validated @NonNull @RequestBody Task task){
        return crudService.addTask(task);
    }

    @PutMapping("/modifyTask/{id}")
    public Task modifyTask(@PathVariable int id,@Validated @NonNull @RequestBody Task task){
        return crudService.modifyTask(id,task);
    }

    @DeleteMapping(value = "/deleteTasks/{ids}")
    public ResponseEntity<ApiResponse> deleteTasks(@PathVariable List<Integer> ids){
        if(crudService.deleteTasks(ids)){
            return new ResponseEntity<>(new ApiResponse<>(200,"success","Tasks deleted successfully"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse<>(500,"fail","Tasks not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
