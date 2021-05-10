package com.digitali.api.repository;

import com.digitali.api.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
     
}
