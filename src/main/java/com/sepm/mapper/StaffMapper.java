package com.sepm.mapper;

import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.entities.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StaffMapper {

    Staff toEntity(StaffPostDto staffPostDto);

    StaffGetDto fromEntity(Staff staff);
}
