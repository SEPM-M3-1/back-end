package com.sepm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sepm.dtos.*;
import com.sepm.entities.Manager;
import com.sepm.entities.Shift;
import com.sepm.mapper.StaffMapper;
import com.sepm.services.ManagerService;
import com.sepm.services.ShiftService;
import com.sepm.services.StaffService;
import com.sepm.services.WorkTimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerService manageService;

    @MockBean
    private ShiftService shiftService;

    @MockBean
    private StaffService staffService;

    @MockBean
    private WorkTimeService workTimeService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void loginTest() throws Exception {
        String inputJson = "{\"email\": \"test@sepm.com\", \"password\":\"Test1m31\"}";
        MvcResult mvcResult = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson)).andReturn();
    }

    @Test
    public void registrationTest() throws Exception {
        StaffPostDto dto = StaffPostDto.builder()
                .email("test1@sepm.com")
                .fullName("Sharlene")
                .phone("0432435698")
                .address("123 Swanston Street, Melbourne 3003")
                .password("Test-m31")
                .build();
        mockMvc.perform(
                post("/registration")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void signupTest() throws Exception {
        ManagerPostDto dto = ManagerPostDto.builder()
                .email("test1@sepm.com")
                .fullName("Sharlene")
                .phone("0432435698")
                .password("Test-m31")
                .build();
        mockMvc.perform(
                post("/signup")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void passwordResetTest() throws Exception {
        PasswordResetDto passwordResetDto = PasswordResetDto.builder()
                .id(1L)
                .oldPassword("Test-m31")
                .password("Test_m31")
                .type(Type.MANAGER)
                .build();

        mockMvc.perform(
                put("/passwordreset")
                        .content(objectMapper.writeValueAsString(passwordResetDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void setWorkTimeTest() throws Exception {
        WorkTimeDto workTimeDto = WorkTimeDto.builder()
                .endDate(13131231231L)
                .startDate(3353534535L)
                .build();
        when(workTimeService.createWorkTime(1L,workTimeDto)).thenReturn(true);
        mockMvc.perform(
                post("/staff/1/settime")
                        .content(objectMapper.writeValueAsString(workTimeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void availableStaffTest() throws Exception {

        when(workTimeService.fetchUserListByWorkTime(3353534535L,13131231231L)).thenReturn(new ArrayList<WorkTimeGetDto>());
        mockMvc.perform(
                get("/avilablestaff")
                        .param("startDate", String.valueOf(3353534535L))
                        .param("endDate",String.valueOf(13131231231L))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void createShiftTest() throws Exception {

        ShiftPostDto shiftPostDto = ShiftPostDto.builder()
                .startDate(3353534535L)
                .endDate(13131231231L)
                .location("Melbourne")
                .allocated(1L)
                .build();
        mockMvc.perform(
                post("/createshift")
                        .content(objectMapper.writeValueAsString(shiftPostDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void staffProfileTest() throws Exception {
        String email = "test@sepm.com";
        when(staffService.fetchProfileByEmail(email)).thenReturn(new StaffProfileDto());

        mockMvc.perform(
                get("/staffprofile")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void changeStaffProfileTest() throws Exception {
        StaffProfileDto staffProfileDto = StaffProfileDto.builder().build();

        mockMvc.perform(
                put("/staffprofile/change")
                        .content(objectMapper.writeValueAsString(staffProfileDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void managerProfileTest() throws Exception {
        String email = "test@sepm.com";
        when(manageService.fetchProfileByEmail(email)).thenReturn(new ManagerProfileDto());

        mockMvc.perform(
                get("/managerprofile")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void changeManagerProfileTest() throws Exception {
        ManagerProfileDto managerProfileDto = ManagerProfileDto.builder().build();

        mockMvc.perform(
                put("/managerprofile/change")
                        .content(objectMapper.writeValueAsString(managerProfileDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }

    @Test
    public void allManagersTest() throws Exception {
        mockMvc.perform(
                get("/allmanagers"))
                .andExpect(status().isOk());
    }

    @Test
    public void allStaffTest() throws Exception {
        mockMvc.perform(
                get("/allstaff"))
                .andExpect(status().isOk());
    }

    @Test
    public void changeStaffHourLimits() throws Exception {
        LimitTimeChangeDto limitTimeChangeDto = LimitTimeChangeDto.builder().build();

        mockMvc.perform(
                put("/changehourlimits")
                .content(objectMapper.writeValueAsString(limitTimeChangeDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

}
