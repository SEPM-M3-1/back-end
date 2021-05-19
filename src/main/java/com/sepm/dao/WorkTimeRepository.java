package com.sepm.dao;

import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {

            @Query(nativeQuery = true, value = "select * " +
                    "from WorkTime wt " +
                    "where wt.start_date>=:startDate and wt.end_date<=:endDate ")
            List<WorkTime> JustifyWorkTime(@Param("startDate") Long startDate,
                                           @Param("endDate") Long endDate);

}