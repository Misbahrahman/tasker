package com.example.tasker.controller;


import com.example.tasker.repo.TaskRepo;
import com.example.tasker.service.TaskService;
import com.example.tasker.model.Task;
import com.example.tasker.transformer.TaskRequest;
import com.example.tasker.transformer.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;


    @PostMapping("add_task")
    ResponseEntity addTask(@RequestBody TaskRequest taskReq){
        try{
            taskService.addTask(taskReq);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("success" , HttpStatus.OK);
    }


    @GetMapping("get_task_of_user")
    ResponseEntity getAllTaskOfUser(@RequestParam("user_id") int userid){

        try{
            List<TaskResponse> list = taskService.getAllTaskOfUser(userid);
            return new ResponseEntity(list , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("close_task")
    ResponseEntity taskStatusClose(@RequestParam("task_id") int id){
        try{
            taskService.taskStatusClose(id);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Closed" , HttpStatus.OK);
    }



    //listall
    //expediate(send mail)
    //del all completed task




}
