package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffGetDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String fullName;

    private String preferredName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String address;

    @NotEmpty
    private Integer hourLimits;
}
