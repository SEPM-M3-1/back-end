package com.sepm.mapper;

import com.sepm.dtos.*;
import com.sepm.entities.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {

    Manager toEntity(ManagerPostDto managerPostDto);

    ManagerGetDto fromEntity(Manager manager);

    ManagerProfileDto profileFromEntity (Manager manager);

    LoginGetDto loginDtoFromEntity(Manager manager);

}
