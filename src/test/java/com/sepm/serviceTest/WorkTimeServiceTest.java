package com.sepm.serviceTest;

import com.sepm.dao.StaffRepository;
import com.sepm.dao.WorkTimeRepository;
import com.sepm.dtos.WorkTimeDto;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import com.sepm.mapper.WorkTimeMapper;
import com.sepm.services.WorkTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class WorkTimeServiceTest {
    @Mock
    private WorkTimeService workTimeService;

    @Mock
    private WorkTimeRepository workTimeRepository;

    @Autowired
    private WorkTimeMapper workTimeMapper;

    @Mock
    private StaffRepository staffRepository;

    @BeforeEach()
    public void setup() {
        workTimeService = new WorkTimeService(
                workTimeRepository,
                workTimeMapper,
                staffRepository
        );
    }

    @Test
    public void changePasswordSuccessful() {
        WorkTime workTimeItem = WorkTime.builder()
                .id(2L)
                .startDate(111L)
                .endDate(222L)
                .build();

        List<WorkTime> workTimeList = new ArrayList<>();
        workTimeList.add(workTimeItem);

        Staff returnedStaff = Staff.builder()
                .id(1L)
                .email("email")
                .fullName("fullName")
                .preferredName("nickName")
                .phone("0481111111")
                .hourLimits(40)
                .address("address")
                .password("password")
                .worktime(workTimeList)
                .build();

        WorkTime workTime = WorkTime.builder()
                .id(1L)
                .startDate(111L)
                .endDate(222L)
                .staff(returnedStaff)
                .build();

        WorkTimeDto workTimeDto = WorkTimeDto.builder()
                .endDate(222L)
                .startDate(111L)
                .build();

        when(staffRepository.findById(1L))
                .thenReturn(Optional.of(returnedStaff));
        boolean resetStatus =
                workTimeService.createWorkTime(1L, workTimeDto);
        assertEquals(true, resetStatus);

    }
}
