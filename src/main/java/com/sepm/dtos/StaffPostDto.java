package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPostDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private Integer hourLimits;

    @NotEmpty
    private String address;

    @NotEmpty
    @Pattern(regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*[0-9])(?=\\S*[#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8}$")
    private String password;


}
