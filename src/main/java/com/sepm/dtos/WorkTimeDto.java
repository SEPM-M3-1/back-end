package com.sepm.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTimeDto {



    @NotEmpty
    private String title;

    //Mon Mar 16
    @NotEmpty
    private Date endDate;
    // Tue May 18 21:31:13 CST 2021

    @NotEmpty
    private Date startDate;




}