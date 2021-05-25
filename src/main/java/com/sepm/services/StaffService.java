package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dtos.PasswordResetDto;
import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.entities.Manager;
import com.sepm.entities.Staff;
import com.sepm.exception.ManagerNotFundException;
import com.sepm.exception.StaffAlreadyExistException;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        throw new StaffAlreadyExistException("Staff Already Exist");
    }

    public boolean emailExists(String email) {
        return staffRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public boolean changePassword(PasswordResetDto passwordResetDto) {

        Staff returnedStaff = staffRepository.findById(passwordResetDto.getId())
                .orElseThrow(() -> new StaffNotFundException("Can not find Staff!"));

        if (returnedStaff.getPassword().equals(passwordResetDto.getOldPassword())){
            staffRepository.updatePasswordById(passwordResetDto.getPassword(), passwordResetDto.getId());
            return true;
        }
        return false;
    }





}
