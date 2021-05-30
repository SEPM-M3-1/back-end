package com.sepm.serviceTest;

import com.sepm.dao.StaffRepository;
import com.sepm.dtos.*;
import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import com.sepm.exception.StaffNotFundException;
import com.sepm.mapper.StaffMapper;
import com.sepm.services.EmailService;
import com.sepm.services.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class StaffServiceTest {
    @Mock
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StaffMapper mapper;


    @BeforeEach()
    public void setup() {
        staffService = new StaffService(
                staffRepository,
                mapper,
                emailService
        );
    }

    @Test
    public void staffLoginFailWhenLoginInfoWrong() {

        String email = "test@gmail.com";
        String password = "password";
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

        when(staffRepository.findByEmail(email)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> staffService.staffLogin(email, password));

    }

    @Test
    public void staffLoginSuccessful() {

        String email = "test@gmail.com";
        String password = "password";
        Staff returnedStaff = Staff.builder()
                .id(1L)
                .password(password)
                .address("address")
                .hourLimits(40)
                .phone("0481111111")
                .fullName("fullName")
                .email(email)
                .build();

        when(staffRepository.findByEmail(email)).thenReturn(Optional.of(returnedStaff));
        LoginGetDto loginDto = staffService.staffLogin(email, password);
        assertEquals("fullName", loginDto.getFullName());
    }


    @Test
    public void createStaffSuccessfulWhenGivenStaffDto() {

        StaffPostDto staffPostDto = StaffPostDto.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        Staff staff = Staff.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        ArgumentCaptor<Staff> captor = ArgumentCaptor.forClass(Staff.class);

        when(staffRepository.save(staff)).thenReturn(staff);
        staffService.createStaff(staffPostDto);

        verify(staffRepository).save(captor.capture());
        Staff savedStaff = captor.getValue();
        assertEquals("user@gmail.com", savedStaff.getEmail());
    }

    @Test
    public void changePasswordSuccessful() {

        Staff staff = Staff.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        PasswordResetDto passwordResetDto = PasswordResetDto.builder()
                .id(1L)
                .oldPassword("oldPassword")
                .password("password")
                .type(Type.STAFF)
                .build();

        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        boolean resetStatus = staffService.changePassword(passwordResetDto);
        assertEquals(false, resetStatus);
    }

    @Test
    public void fetchProfileByEmailFail() {

        String email = "test1@gmail.com";
        String password = "password1";
        Staff staff = Staff.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        when(staffRepository.findByEmail("test@email")).thenReturn(Optional.ofNullable(staff));
        assertThrows(StaffNotFundException.class, () -> staffService.fetchProfileByEmail(email));

    }

    @Test
    public void fetchProfileByEmailSuccessful() {
        String email = "test@gmail.com";
        String password = "password";
        Staff staff = Staff.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        PasswordResetDto passwordResetDto = PasswordResetDto.builder()
                .id(1L)
                .oldPassword("oldPassword")
                .password("password")
                .type(Type.STAFF)
                .build();

        when(staffRepository.findByEmail(email)).thenReturn(Optional.of(staff));
        StaffProfileDto staffProfileDto = staffService.fetchProfileByEmail(email);
        assertEquals("user@gmail.com", staffProfileDto.getEmail());
    }

}
