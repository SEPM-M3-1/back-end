package com.sepm.services;

import com.sepm.dao.ManagerRepository;
import com.sepm.dtos.*;
import com.sepm.entities.Manager;
import com.sepm.entities.Staff;
import com.sepm.exception.BadCredentialsException;
import com.sepm.exception.ManagerNotFundException;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.ManagerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final EmailService emailService;
    private final ManagerMapper mapper;

    public LoginGetDto managerLogin(String email,String password){
        if(emailExists(email)){
            Manager manager = managerRepository.findByEmail(email).get();
            if( manager.getPassword().equals(password)) {
                return mapper.loginDtoFromEntity(manager);
            }
        }
        throw new BadCredentialsException("Email or password is incorrect");
    }

    public ManagerGetDto createManager(ManagerPostDto managerPostDto){
        if(!emailExists(managerPostDto.getEmail())){
            Manager test = mapper.toEntity(managerPostDto);
            log.info("测试： "+test);
            Manager manager = managerRepository.save(mapper.toEntity(managerPostDto));
            String subject = "Confirmation of new staff account of " + manager.getFullName();
            String content = "Your password is " + manager.getPassword() + ". Please login with your email.";
            emailService.sendEMail(manager.getEmail(),subject,content);
            return mapper.fromEntity(manager);
        }
        return null;
    }


    private boolean emailExists(String email) {
        return managerRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public boolean changePassword(PasswordResetDto passwordResetDto) {

        Manager returnedManager = managerRepository.findById(passwordResetDto.getId())
                .orElseThrow(() -> new ManagerNotFundException("Can not find Manager!"));

        if (returnedManager.getPassword().equals(passwordResetDto.getOldPassword())){
            managerRepository.updatePasswordById(passwordResetDto.getPassword(),passwordResetDto.getId());
            return true;
        }
        return false;
    }

    public List<ManagerProfileDto> fetchAllManagers(){
        return  managerRepository.findAll().stream()
                .map(manager -> mapper.profileFromEntity(manager))
                .collect(Collectors.toList());
    }

    public ManagerProfileDto fetchProfileByEmail(String email) {
        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new ManagerNotFundException("No such manager found"));
        return mapper.profileFromEntity(manager);
    }

    @Transactional
    public ManagerProfileDto changeManagerProfile (ManagerProfileDto dto) {
        managerRepository.updateProfileById(dto.getId(), dto.getEmail(), dto.getFullName(), dto.getPhone());
        return mapper.profileFromEntity(managerRepository.findById(dto.getId()).get());
    }

}
