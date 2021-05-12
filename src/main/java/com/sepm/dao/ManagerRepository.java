package com.sepm.dao;

import com.sepm.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);

}
