package com.example.tasker.service;

import com.example.tasker.customException.CustomException;
import com.example.tasker.model.Task;
import com.example.tasker.model.User;
import com.example.tasker.repo.TaskRepo;
import com.example.tasker.repo.UserRepo;
import com.example.tasker.transformer.TaskRequest;
import com.example.tasker.transformer.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;


    public void addTask(TaskRequest taskreq) {
        Optional<User> userOptional = userRepo.findById(taskreq.getAssignedToId());

        if(userOptional.isEmpty())throw new CustomException("Invalid User Id");

        Task task = Task.builder()
                .assignedTo(userOptional.get())
                .title(taskreq.getTitle())
                .completed(taskreq.isCompleted())
                .description(taskreq.getDescription())
                .dueDate(taskreq.getDueDate())
                .priority(taskreq.getPriority())
                .build();

        Task savedTask =  taskRepo.save(task);

        //cascading manually
        User user =  userOptional.get();
        user.getTasks().add(savedTask);
        userRepo.save(user);

    }

    public void taskStatusClose(int id) {

        Optional<Task> optionalTask = taskRepo.findById(id);
        if(optionalTask.isEmpty())throw new CustomException("Invalid id");

        Task task = optionalTask.get();
        task.setCompleted(true);
        taskRepo.save(task);

    }

    public List<TaskResponse> getAllTaskOfUser(int userid) {
        Optional<User> optionalUser = userRepo.findById(userid);
        if(optionalUser.isEmpty())throw new CustomException("Invalid id");

        List<Task> tasks = optionalUser.get().getTasks();
        List<TaskResponse> list = new ArrayList<>();

        for(Task task : tasks){
            TaskResponse taskResponse = TaskResponse.builder()
                    .title(task.getTitle())
                    .assignedToName(task.getAssignedTo().getName())
                    .completed(task.isCompleted())
                    .description(task.getDescription())
                    .dueDate(task.getDueDate())
                    .priority(task.getPriority())
                    .build();

            list.add(taskResponse);
        }

        return list;


    }
}
