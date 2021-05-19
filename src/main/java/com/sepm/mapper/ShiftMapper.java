package com.sepm.mapper;

import com.sepm.dtos.*;
import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShiftMapper {
    Shift toEntity(ShiftPostDto shiftPostDto);

    ShiftGetDto fromEntity(Shift shift);
}
