package com.project.demo.dto.request;
import java.time.LocalDate;

public record DataUpdatePaciente(
        Long id,
        String nombre,
        String apellido,
        String dni,
        LocalDate fechaAlta,
        DomicilioDTO domicilio
) {
}

