package com.sepm.serviceTest;

import com.sepm.dao.ShiftRepository;
import com.sepm.dtos.ShiftPostDto;
import com.sepm.entities.Shift;
import com.sepm.entities.Status;
import com.sepm.mapper.ShiftMapper;
import com.sepm.services.ShiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ShiftServiceTest {

    @Mock
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftMapper shiftMapper;

    @Autowired
    private ShiftService shiftService;

    @BeforeEach()
    public void setup() {
        shiftService = new ShiftService(
                shiftRepository,
                shiftMapper
        );
    }

    @Test
    public void createShiftTimeSuccessfulWhenGivenInfo() {

        ShiftPostDto shiftPostDto = ShiftPostDto.builder()
                .endDate(11L)
                .startDate(22L)
                .location("location")
                .allocated(2L)
                .build();

        Shift shift = Shift.builder()
                .id(1L)
                .startDate(111L)
                .endDate(222L)
                .location("location")
                .requestExchange(333L)
                .allocated(2L)
                .status(Status.ACCEPTED)
                .build();

        ArgumentCaptor<Shift> captor = ArgumentCaptor.forClass(Shift.class);

        when(shiftRepository.save(shift)).thenReturn(shift);
        shiftService.createShift(shiftPostDto);

        verify(shiftRepository).save(captor.capture());
        Shift savedShift = captor.getValue();
        assertEquals("location", savedShift.getLocation());
    }
}
