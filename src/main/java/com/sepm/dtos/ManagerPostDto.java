package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerPostDto {

    private String fullName;

    @Email
    private String email;

    private String phone;

    @Pattern(regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*[0-9])(?=\\S*[#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8}$")
    private String password;
}
