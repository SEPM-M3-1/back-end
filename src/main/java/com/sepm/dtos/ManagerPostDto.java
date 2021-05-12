package com.sepm.dtos;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerPostDto {

    private String fullName;

    @Email
    private String email;

    private String phone;

    private String password;
}
