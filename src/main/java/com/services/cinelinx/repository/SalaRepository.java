package com.services.cinelinx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.services.cinelinx.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

}
