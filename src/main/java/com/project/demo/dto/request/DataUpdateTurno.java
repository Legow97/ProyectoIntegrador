package com.project.demo.dto.request;
import java.time.LocalDate;
public record DataUpdateTurno(
        Long id, LocalDate fecha,
        Long odontologoId,
        Long pacienteId

) { }

