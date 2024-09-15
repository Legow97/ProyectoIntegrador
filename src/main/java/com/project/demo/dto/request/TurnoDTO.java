package com.project.demo.dto.request;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record TurnoDTO(Long id,
                       LocalDate fecha,
                       Long odontologoId,
                       Long pacienteId
) { }


