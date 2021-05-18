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
public class WorkTimeGetDto {

    @NotEmpty
    private Long userId;
    @NotEmpty
    private String userName;

    @NotEmpty
    private Date endDate;

    @NotEmpty
    private Date startDate;

}
