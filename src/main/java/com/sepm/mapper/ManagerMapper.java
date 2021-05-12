package com.sepm.mapper;

import com.sepm.dtos.ManagerGetDto;
import com.sepm.dtos.ManagerPostDto;
import com.sepm.entities.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    Manager toEntity(ManagerPostDto managerPostDto);

    ManagerGetDto fromEntity(Manager manager);

}
