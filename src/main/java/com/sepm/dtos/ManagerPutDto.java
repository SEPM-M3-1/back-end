package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerPutDto {

    private Long id;

    private String fullName;

    @Email
    private String email;

    private String phone;

    private String password;
}
