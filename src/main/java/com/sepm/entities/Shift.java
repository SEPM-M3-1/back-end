package com.sepm.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shift")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private Long startDate;

    @Column(name = "endDate", nullable = false)
    private Long endDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "requestExchange", nullable = false)
    private String requestExchange;

    @Column(name = "allocated", nullable = false)
    private String allocated;
}

