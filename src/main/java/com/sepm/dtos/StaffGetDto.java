package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffGetDto {

    private Long id;

    private String email;

    private String fullName;

    private String preferredName;

    private String phone;

    private String address;

    private Integer hourLimits;
}
