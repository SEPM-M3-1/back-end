package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dao.WorkTimeRepository;
import com.sepm.dtos.*;
import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.ShiftMapper;
import com.sepm.mapper.StaffMapper;
import com.sepm.mapper.WorkTimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;
    private final WorkTimeMapper workTimeMapper;
    private final StaffRepository staffRepository;


    public boolean createWorkTime(Long ownerId,WorkTimeDto Dto) {
        Staff staff=staffRepository.findById(ownerId).
                orElseThrow(()->new StaffNotFundException("not find this staff"));

        WorkTime workTime =workTimeMapper.toEntity(Dto);
        workTime.setStaff(staff);
        workTimeRepository.save(workTime);

        return true;

    }

    public List<WorkTimeGetDto> fetchUserListByWorkTime(Long startTime, Long endTime){
        List<WorkTime> userList = workTimeRepository.JustifyWorkTime(startTime, endTime);
        List<WorkTimeGetDto> listOfDtos = new ArrayList<>();
        if(userList.size()>0){
            listOfDtos = userList.stream().map(user->WorkTimeGetDto.builder().
                    id(user.getStaff().getId()).userName(user.getStaff().getFullName()).startDate(user.getStartDate()).endDate(user.getEndDate()).build()).collect(Collectors.toList());
        }

        return listOfDtos;
    };


}
