package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.entities.Manager;
import com.sepm.entities.Staff;
import com.sepm.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public boolean staffLogin(String email, String password) {
        if(emailExists(email)){
            Staff staff = staffRepository.findByEmail(email).get();
            return staff.getPassword().equals(password);
        }
        return false;
    }

    public StaffGetDto createStaff(StaffPostDto staffPostDto) {
        if(!emailExists(staffPostDto.getEmail())){
            Staff staff = staffRepository.save(staffMapper.toEntity(staffPostDto));
            return staffMapper.fromEntity(staff);
        }
        return null;
    }

    private boolean emailExists(String email) {
        return staffRepository.findByEmail(email).isPresent();
    }
}
