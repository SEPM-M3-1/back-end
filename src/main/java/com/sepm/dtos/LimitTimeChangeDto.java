package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitTimeChangeDto {

    @NotEmpty
    private Integer hourLimits;

    @NotEmpty
    private Long id;
}
