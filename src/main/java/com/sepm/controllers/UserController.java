package com.sepm.controllers;

import com.sepm.dtos.*;
import com.sepm.services.ManagerService;
import com.sepm.services.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
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
    public ResponseEntity staffRegistration(@Valid @RequestBody StaffPostDto dto) {
        return new ResponseEntity(staffService.createStaff(dto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity managerSignup(@Valid @RequestBody ManagerPostDto dto) {
        log.info("邮箱",dto.getEmail());
        return new ResponseEntity(managerService.createManager(dto), HttpStatus.OK);
    }

    @PutMapping("/passwordreset")
    public ResponseEntity changePassword(@RequestBody PasswordResetDto passwordResetDto) {
        if (Type.MANAGER.equals(passwordResetDto.getType())) {
            return new ResponseEntity(managerService.changePassword(passwordResetDto), HttpStatus.OK);
        }
        return new ResponseEntity(staffService.changePassword(passwordResetDto), HttpStatus.OK);
    }




}
