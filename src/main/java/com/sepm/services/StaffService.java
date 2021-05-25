package com.sepm.services;

import com.sepm.dao.StaffRepository;
import com.sepm.dtos.*;
import com.sepm.entities.Manager;
import com.sepm.entities.Staff;
import com.sepm.exception.BadCredentialsException;
import com.sepm.exception.ManagerNotFundException;
import com.sepm.exception.StaffAlreadyExistException;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    public LoginGetDto staffLogin(String email, String password){
        if(emailExists(email)){
            Staff staff = staffRepository.findByEmail(email).get();
            if( staff.getPassword().equals(password)) {
                return staffMapper.loginDtoFromEntity(staff);
            }
        }
        throw new BadCredentialsException("Email or password is incorrect");
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

    public List<StaffProfileDto> fetchAllStaff(){
        return  staffRepository.findAll().stream()
                .map(staff -> staffMapper.profileFromEntity(staff))
                .collect(Collectors.toList());
    }

    public StaffProfileDto fetchProfileByEmail(String email) {
        Staff staff = staffRepository.findByEmail(email)
                .orElseThrow(() -> new StaffNotFundException("No such staff found"));
        return staffMapper.profileFromEntity(staff);
    }

    @Transactional
    public StaffProfileDto changeStaffProfile (StaffProfileDto dto) {
        staffRepository.updateProfileById(dto.getId(), dto.getEmail(), dto.getFullName(), dto.getPhone(), dto.getPreferredName(), dto.getAddress());
        return staffMapper.profileFromEntity(staffRepository.findById(dto.getId()).get());
    }


}
