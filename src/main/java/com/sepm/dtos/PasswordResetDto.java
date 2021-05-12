package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    @Pattern(regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*[0-9])(?=\\S*[#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8}$")
    private String password;

    @NotEmpty
    private Type type;

}
