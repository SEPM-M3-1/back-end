package com.sepm.services;

import com.sepm.dao.ShiftRepository;
import com.sepm.dao.StaffRepository;
import com.sepm.dtos.ShiftGetDto;
import com.sepm.dtos.ShiftPostDto;
import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import com.sepm.mapper.ShiftMapper;
import com.sepm.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;


    public ShiftGetDto createShift(ShiftPostDto Dto) {

        Shift shift = shiftRepository.save(shiftMapper.toEntity(Dto));
        return shiftMapper.fromEntity(shift);

    }


}
