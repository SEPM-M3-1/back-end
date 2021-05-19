package com.sepm.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "hour_limits", nullable = false)
    private Integer hourLimits;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<WorkTime> worktime;

//    @OneToMany(mappedBy = "staff",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    private List<Shift> Shift;

}
