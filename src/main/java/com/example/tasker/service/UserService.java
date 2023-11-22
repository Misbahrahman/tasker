package com.example.tasker.service;

import com.example.tasker.model.User;
import com.example.tasker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    public void addUser(String name , String email) {

        User user = User.builder()
                .name(name)
                .tasks(new ArrayList<>())
                .email(email)
                .build();

        repo.save(user);

    }

    public List<User> getUserList() {
        List<User> users = repo.findAll();
        return users;
    }

}
