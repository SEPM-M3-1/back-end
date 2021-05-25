package com.sepm.entities;

import lombok.*;

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

    @Column(name = "start_date", nullable = false)
    private Long startDate;

    @Column(name = "end_date", nullable = false)
    private Long endDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "request_exchange")
    private Long requestExchange;

    @Column(name = "allocated", nullable = false)
    private Long allocated;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @ManyToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JoinColumn(name = "staffid",
//            referencedColumnName = "id",
//            nullable = false)
//    private Staff staff;
}

