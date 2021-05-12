package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPostDto {

    @Email
    private String email;

    private String fullName;

    private String phone;

    private Integer hourLimits;

    private String address;

    private String password;
}
