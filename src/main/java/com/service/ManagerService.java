package com.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ManagerService {
    @Autowired
    ManagerDao managerDao;

    public boolean managerLogin(String email,String password){
        if(managerDao.findById(email).isPresent()){
            Manager manager=managerDao.findById(email).get();
            if(manager.getMPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
