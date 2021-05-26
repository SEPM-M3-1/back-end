package com.sepm.controllers;

import com.sepm.dtos.*;
import com.sepm.services.ManagerService;
import com.sepm.services.ShiftService;
import com.sepm.services.StaffService;
import com.sepm.services.WorkTimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final ManagerService managerService;
    private final StaffService staffService;
    private final WorkTimeService workTimeService;
    private final ShiftService shiftService;

    @PostMapping("/login")//done
    public ResponseEntity login(@RequestBody LoginDto loginDto){

        if("Manager".equals(loginDto.getType())){
            return new ResponseEntity(managerService.managerLogin(loginDto.getEmail(), loginDto.getPassword()), HttpStatus.OK);
        }else {
            return new ResponseEntity(staffService.staffLogin(loginDto.getEmail(), loginDto.getPassword()), HttpStatus.OK);
        }
    }

    @PostMapping("/registration")//done
    public ResponseEntity staffRegistration(@Valid @RequestBody StaffPostDto dto) {
        log.info("aaa",dto.getHourLimits());
        return new ResponseEntity(staffService.createStaff(dto), HttpStatus.OK);
    }

    @PostMapping("/signup")//done
    public ResponseEntity managerSignup(@Valid @RequestBody ManagerPostDto dto) {
        log.info("邮箱",dto.getEmail());
        return new ResponseEntity(managerService.createManager(dto), HttpStatus.OK);
    }

    @PutMapping("/passwordreset")//done
    public ResponseEntity changePassword(@RequestBody PasswordResetDto passwordResetDto) {
        log.info(Type.STAFF.equals(passwordResetDto.getType()) + " :!staff type!");
        if (Type.MANAGER.equals(passwordResetDto.getType())) {
            return new ResponseEntity(managerService.changePassword(passwordResetDto), HttpStatus.OK);
        } else if (Type.STAFF.equals(passwordResetDto.getType())) {
            return new ResponseEntity(staffService.changePassword(passwordResetDto), HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/staff/{ownerId}/settime")
    public ResponseEntity setAvaliableTime(@Valid @PathVariable("ownerId") Long ownerId ,
                                           @RequestBody WorkTimeDto dto) {


        if(workTimeService.createWorkTime(ownerId,dto)){
            return new ResponseEntity(true,HttpStatus.OK);
        }
        return new ResponseEntity(false,HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/avilablestaff")
    public ResponseEntity avilableStaff(@RequestParam("startDate") Long startTime, @RequestParam("endDate") Long endTime) {
        return new ResponseEntity(workTimeService.fetchUserListByWorkTime(startTime, endTime), HttpStatus.OK);


    }

    @PostMapping("/createshift")
    public ResponseEntity createshift(@RequestBody ShiftPostDto dto) {

        return new ResponseEntity(shiftService.createShift(dto), HttpStatus.OK);

    }

    @GetMapping("/staffprofile")//done
    public ResponseEntity fetchStaffProfileByEmail(@RequestParam("email") String email) {

        return new ResponseEntity(staffService.fetchProfileByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/staffprofile/change")
    public ResponseEntity changeStaffProfile(@RequestBody StaffProfileDto dto) {
        return new ResponseEntity(staffService.changeStaffProfile(dto), HttpStatus.OK);
    }

    @GetMapping("/managerprofile")//done
    public ResponseEntity fetchManagerProfileByEmail(@RequestParam("email") String email) {

        return new ResponseEntity(managerService.fetchProfileByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/managerprofile/change")//done
    public ResponseEntity changeManagerProfile(@RequestBody ManagerProfileDto dto){
        return new ResponseEntity(managerService.changeManagerProfile(dto), HttpStatus.OK);
    }

    @GetMapping("/allmanagers")//done
    public ResponseEntity fetAllManagers() {
        return new ResponseEntity(managerService.fetchAllManagers(), HttpStatus.OK);
    }

    @GetMapping("/allstaff")//done
    public ResponseEntity fetAllStaff() {
        return new ResponseEntity(staffService.fetchAllStaff(), HttpStatus.OK);
    }


    @PutMapping("/changehourlimits")//done
    public ResponseEntity changeStaffHourLimits(@RequestBody LimitTimeChangeDto limitTimeChangeDto) {
        staffService.changeHourLimits(limitTimeChangeDto.getHourLimits(), limitTimeChangeDto.getId());
        return new ResponseEntity("Succeed to change the hour limits!", HttpStatus.OK);
    }

}
