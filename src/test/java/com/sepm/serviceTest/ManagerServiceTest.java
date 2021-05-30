package com.sepm.serviceTest;

import com.sepm.dao.ManagerRepository;
import com.sepm.dtos.LoginGetDto;
import com.sepm.dtos.ManagerPostDto;
import com.sepm.dtos.PasswordResetDto;
import com.sepm.dtos.Type;
import com.sepm.entities.Manager;
import com.sepm.exception.BadCredentialsException;
import com.sepm.mapper.ManagerMapper;
import com.sepm.services.EmailService;
import com.sepm.services.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ManagerMapper mapper;


    @BeforeEach()
    public void setup() {
        managerService = new ManagerService(
                managerRepository,
                emailService,
                mapper
        );
    }

    @Test
    public void managerLoginFailWhenLoginInfoCorrect() {

        String email = "test1@gmail.com";
        String password = "password1";
        Manager returnedManager = Manager.builder()
                .id(1L)
                .password("password")
                .phone("0481111111")
                .fullName("fullName")
                .email("email")
                .build();

        when(managerRepository.findByEmail(email)).thenReturn(Optional.ofNullable(returnedManager));
        assertThrows(BadCredentialsException.class, () -> managerService.managerLogin(email, password));

    }

    @Test
    public void managerLoginSuccessfulWhenLoginInfoCorrect() {

        String email = "test@gmail.com";
        String password = "password";
        Manager returnedManager = Manager.builder()
                .id(1L)
                .password(password)
                .phone("0481111111")
                .fullName("fullName")
                .email(email)
                .build();

        when(managerRepository.findByEmail(email)).thenReturn(Optional.of(returnedManager));
        LoginGetDto loginDto = managerService.managerLogin(email, password);
        assertEquals("fullName", loginDto.getFullName());

    }

    @Test
    public void createManagerSuccessfulWhenGivenManagerDto() {

        ManagerPostDto managerPostDto = ManagerPostDto.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        Manager manager = Manager.builder()
                .email("user@gmail.com")
                .password("len123")
                .fullName("user")
                .phone("0481111111")
                .build();

        ArgumentCaptor<Manager> captor = ArgumentCaptor.forClass(Manager.class);

        when(managerRepository.save(manager)).thenReturn(manager);
        managerService.createManager(managerPostDto);

        verify(managerRepository).save(captor.capture());
        Manager savedManager = captor.getValue();
        assertEquals("user@gmail.com", savedManager.getEmail());
    }

    @Test
    public void changePasswordSuccessful() {

        Manager manager = Manager.builder()
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

        when(managerRepository.findById(1L)).thenReturn(Optional.of(manager));
        boolean resetStatus = managerService.changePassword(passwordResetDto);
        assertEquals(false, resetStatus);

    }



}
