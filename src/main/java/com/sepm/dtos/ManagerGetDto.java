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
public class ManagerGetDto {

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

}
