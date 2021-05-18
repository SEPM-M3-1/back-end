package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dao.WorkTimeRepository;
import com.sepm.dtos.*;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.StaffMapper;
import com.sepm.mapper.WorkTimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<WorkTimeGetDto> fetchUserListByWorkTime(Date startTime, Date endTime){
        List<WorkTime> userList = workTimeRepository.JustifyWorkTime(startTime, endTime);
        List<WorkTimeGetDto> listOfDtos = new ArrayList<>();
        if(userList.size()>0){
            listOfDtos = userList.stream().map(workTimeMapper::fromEntity).collect(Collectors.toList());
            listOfDtos.forEach(user -> user.setUserName(staffRepository.findById(user.getUserId()).get().getFullName()));
        }
        return listOfDtos;
    };


}
