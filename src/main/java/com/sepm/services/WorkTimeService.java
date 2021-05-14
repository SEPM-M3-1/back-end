package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dao.WorkTimeRepository;
import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.dtos.WorkTimeDto;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.StaffMapper;
import com.sepm.mapper.WorkTimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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





}
