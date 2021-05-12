package com.sepm.dao;

import com.sepm.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);

    @Modifying
    @Query("update Manager m set m.email=:email, m.fullName=:fullName, m.phone=:phone where m.id=:id")
    int updateProfileById(@Param("id") Long id,
                          @Param("email") String email,
                          @Param("fullName") String fullName,
                          @Param("phone") String phone);

    @Modifying
    @Query("update Manager m set m.password=:password where m.id=:id")
    int updatePasswordById(String password, Long id);


}
