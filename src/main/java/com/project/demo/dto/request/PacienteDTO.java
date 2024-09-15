package com.project.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PacienteDTO(
        Long id, String nombre, String apellido, String dni, LocalDate fechaAlta, DomicilioDTO domicilio
) {
}

