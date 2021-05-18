package com.sepm.mapper;

import com.sepm.dtos.StaffGetDto;
import com.sepm.dtos.StaffPostDto;
import com.sepm.dtos.WorkTimeDto;
import com.sepm.dtos.WorkTimeGetDto;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkTimeMapper {

    WorkTime toEntity(WorkTimeDto workTimeDto);
    WorkTimeGetDto fromEntity(WorkTime workTime);

}
