package com.project.demo.dto.request;

import org.antlr.v4.runtime.misc.NotNull;

public record DataUpdateOdontologo(
        Long id,
        String nombre,
        String apellido,
        String nroMatricula
) {
}
