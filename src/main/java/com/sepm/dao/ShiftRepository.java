package com.sepm.dao;

import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    @Query("select s from Shift s where s.allocated=:id and s.status = 'PENDING'")
    List<Shift> getShiftByStaffId(Long id);

    @Modifying
    @Query("update Shift s set s.status = 'ACCEPTED' where s.id = :id")
    int changeStatus(Long id);

    int deleteShiftById(Long id);
}
