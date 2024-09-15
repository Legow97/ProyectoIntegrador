package com.project.demo.repository;

import com.project.demo.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
}
