package com.project.demo.controller;

import com.project.demo.dto.request.DataUpdatePaciente;
import com.project.demo.dto.request.OdontologoDTO;
import com.project.demo.dto.request.PacienteDTO;
import com.project.demo.entity.Odontologo;
import com.project.demo.entity.Paciente;
import com.project.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/agregar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO nuevoPaciente = pacienteService.guardarPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPaciente(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteOptionalDTO = pacienteService.buscarPacientePorId(id);

        if(pacienteOptionalDTO.isPresent()){
            PacienteDTO oDTO = pacienteOptionalDTO.get();
            return ResponseEntity.ok(oDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> modificarPaciente(@PathVariable Long id, @RequestBody DataUpdatePaciente dataUpdatePaciente) {
        PacienteDTO pacienteActualizado = pacienteService.modificarPaciente(id, dataUpdatePaciente);
        if (pacienteActualizado != null) {
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  eliminarPaciente(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteBuscadoDTO =pacienteService.buscarPacientePorId(id);
        if(pacienteBuscadoDTO.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente Eliminado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }
}
