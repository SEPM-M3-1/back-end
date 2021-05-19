package com.sepm.dao;

import com.sepm.entities.Shift;
import com.sepm.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ShiftRepository extends JpaRepository<Shift, Long> {




}
