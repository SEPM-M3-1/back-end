package com.controller;

import com.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserController {
    @Autowired
    ManagerService managerService;


    @PostMapping
    @ResponseBody
    public ResponseEntity login(String email,String password){
        if(managerService.managerLogin(email, password)){
            return ResponseEntity.ok("success");
        }
        return new ResponseEntity("fail", HttpStatus.BAD_REQUEST);
    }

}
