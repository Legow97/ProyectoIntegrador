package com.project.demo.controller;

import com.project.demo.dto.request.DataUpdateTurno;
import com.project.demo.dto.request.OdontologoDTO;
import com.project.demo.dto.request.PacienteDTO;
import com.project.demo.dto.request.TurnoDTO;
import com.project.demo.entity.Odontologo;
import com.project.demo.entity.Paciente;
import com.project.demo.entity.Turno;
import com.project.demo.service.OdontologoService;
import com.project.demo.service.PacienteService;
import com.project.demo.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping("/agregar")
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turnoDTO) {
        Optional<PacienteDTO> pacienteBuscadoDTO = pacienteService.buscarPacientePorId(turnoDTO.pacienteId());
        Optional<OdontologoDTO> odontologoBuscadoDTO = odontologoService.buscarOdontologoPorId(turnoDTO.odontologoId());

        if (pacienteBuscadoDTO.isPresent() && odontologoBuscadoDTO.isPresent()) {
            Turno turno = new Turno(turnoDTO.fecha(), pacienteService.mapToEntity(pacienteBuscadoDTO.get()), odontologoService.mapToEntity(odontologoBuscadoDTO.get()));
            TurnoDTO nuevoTurno = turnoService.registrarTurno(turno);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTurno);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> obtenerTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoOptional = turnoService.buscarTurnoPorId(id);
        if (turnoOptional.isPresent()) {
            TurnoDTO turnoDTO = turnoOptional.get();
            return ResponseEntity.ok(turnoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDTO> modificarTurno(@PathVariable Long id, @RequestBody DataUpdateTurno dataUpdateTurno) {
        Optional<TurnoDTO> turnoOptional = turnoService.buscarTurnoPorId(id);
        if (turnoOptional.isPresent()) {
            TurnoDTO turnoActualizado = turnoService.modificarTurno(id, dataUpdateTurno);
            return ResponseEntity.ok(turnoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() {

        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno Eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

