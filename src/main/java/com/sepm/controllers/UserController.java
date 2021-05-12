package com.sepm.controllers;

import com.sepm.dtos.LoginDto;
import com.sepm.dtos.ManagerPostDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.services.ManagerService;
import com.sepm.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ManagerService managerService;
    private final StaffService staffService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){

        boolean isValidated;

        if("Manager".equals(loginDto.getType())){
            isValidated = managerService.managerLogin(loginDto.getEmail(), loginDto.getPassword());
        }else {
            isValidated = staffService.staffLogin(loginDto.getEmail(), loginDto.getPassword());
        }
        if(isValidated){
            return new ResponseEntity("success", HttpStatus.OK);
        }
        return new ResponseEntity("fail", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registration")
    public ResponseEntity staffRegistration(StaffPostDto dto) {
        return new ResponseEntity(staffService.createStaff(dto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity managerSignup(ManagerPostDto dto) {
        return new ResponseEntity(managerService.createManager(dto), HttpStatus.OK);
    }



}
