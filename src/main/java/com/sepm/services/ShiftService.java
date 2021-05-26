package com.sepm.services;

import com.sepm.dao.ShiftRepository;
import com.sepm.dao.StaffRepository;
import com.sepm.dtos.ShiftGetDto;
import com.sepm.dtos.ShiftPostDto;
import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import com.sepm.entities.Status;
import com.sepm.mapper.ShiftMapper;
import com.sepm.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;


    public ShiftGetDto createShift(ShiftPostDto Dto) {

        Shift shift = shiftRepository.save(shiftMapper.toEntity(Dto));
        shift.setStatus(Status.PENDING);
        return shiftMapper.fromEntity(shift);
    }

    public List<ShiftGetDto> fetchPendingShiftByStaffId(Long id) {
        return shiftRepository.getShiftByStaffId(id)
                .stream()
                .map(shift -> shiftMapper.fromEntity(shift))
                .collect(Collectors.toList());
    }

    public void acceptShift(Long id) {
        shiftRepository.changeStatus(id);
    }

    @Transactional
    public void deleteShift(Long id) {
        shiftRepository.deleteShiftById(id);
    }


}
