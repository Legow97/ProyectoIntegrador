package com.project.demo.repository;

import com.project.demo.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno,Long> {
}
