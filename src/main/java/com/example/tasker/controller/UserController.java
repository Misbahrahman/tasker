package com.example.tasker.controller;

import com.example.tasker.model.User;
import com.example.tasker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("add_user")
    ResponseEntity addUser(@RequestParam("name") String name , @RequestParam("email") String email){
        userService.addUser(name , email);
        return new ResponseEntity("Success" , HttpStatus.OK);

    }

    @GetMapping("get_all_users")
    ResponseEntity getUserList(){
        List<User> users =  userService.getUserList();
        return new ResponseEntity(users , HttpStatus.FOUND);
    }

}
