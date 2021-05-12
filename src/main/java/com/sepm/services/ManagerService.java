package com.sepm.services;

import com.sepm.dao.ManagerRepository;
import com.sepm.dtos.ManagerGetDto;
import com.sepm.dtos.ManagerPostDto;
import com.sepm.dtos.PasswordResetDto;
import com.sepm.entities.Manager;
import com.sepm.exception.ManagerNotFundException;
import com.sepm.mapper.ManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper mapper;

    public boolean managerLogin(String email,String password){
        if(emailExists(email)){
            Manager manager = managerRepository.findByEmail(email).get();
            return manager.getPassword().equals(password);
        }
        return false;
    }

    public ManagerGetDto createManager(ManagerPostDto managerPostDto){
        if(!emailExists(managerPostDto.getEmail())){
            Manager manager = managerRepository.save(mapper.toEntity(managerPostDto));
            return mapper.fromEntity(manager);
        }
        return null;
    }

    /*public void changePassword(ManagerPutDto managerPutDto){
        if(managerPutDto.getPassword().equals(managerRepository.findById(managerPutDto.getId()))){

        }
    }*/

    private boolean emailExists(String email) {
        return managerRepository.findByEmail(email).isPresent();
    }

    public boolean changePassword(PasswordResetDto passwordResetDto) {

        Manager returnedManager = managerRepository.findById(passwordResetDto.getId())
                .orElseThrow(() -> new ManagerNotFundException("Can not find Manager!"));

        if (returnedManager.getPassword().equals(passwordResetDto.getOldPassword())){
            managerRepository.updatePasswordById(passwordResetDto.getPassword(),passwordResetDto.getId());
            return true;
        }
        return false;
    }
}
