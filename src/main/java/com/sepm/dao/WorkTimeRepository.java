package com.sepm.dao;

import com.sepm.entities.Staff;
import com.sepm.entities.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {



//
//        @Modifying
//        @Query("update WorkTime wt " +
//                "set s.start_time=:start_time, " +
//                "s.end_time=:end_time, " +
//                "s.id=:id, " +
//                "s.preferredName=:preferredName, " +
//                "s.address=:address " +
//                "where s.id=:id")
//        int updateProfileById (@Param("id") Long id,
//            @Param("email") String email,
//            @Param("fullName") String fullName,
//            @Param("phone") String phone,
//            @Param("preferredName") String preferredName,
//            @Param("address") String address);
//
//        @Modifying
//        @Query("update Staff s set s.password=:password where s.id=:id")
//        int updatePasswordById (String password, Long id);
//
//        @Modifying
//        @Query("update Staff s set s.hourLimits=:hourLimits where s.id=:id")
//        int updateHourLimitsById (@Param("hourLimits") Integer hourLimits, @Param("id") Long id);


}