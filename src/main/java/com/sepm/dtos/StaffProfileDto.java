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
public class StaffProfileDto {

    private Long id;

    private String email;

    private String fullName;

    private String preferredName;

    private String phone;

    private String address;

    private Integer hourLimits;
}
