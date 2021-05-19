package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftPutDto {
    @NotEmpty
    private Long endDate;

    @NotEmpty
    private Long startDate;

    @NotEmpty
    private String location;

    @NotEmpty
    private Long allocated;

}
