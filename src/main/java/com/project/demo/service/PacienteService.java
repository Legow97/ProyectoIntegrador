package com.project.demo.service;

import com.project.demo.dto.request.DataUpdatePaciente;
import com.project.demo.dto.request.DomicilioDTO;
import com.project.demo.dto.request.PacienteDTO;
import com.project.demo.entity.Domicilio;
import com.project.demo.entity.Paciente;
import com.project.demo.repository.IPacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    @Autowired
    private IPacienteRepository pacienteRepository;



    public PacienteDTO mapToDTO(Paciente paciente) {
        DomicilioDTO domicilioDTO = new DomicilioDTO(paciente.getDomicilio().getId(), paciente.getDomicilio().getCalle(),
                paciente.getDomicilio().getNumero(), paciente.getDomicilio().getLocalidad(), paciente.getDomicilio().getProvincia());

        return new PacienteDTO(paciente.getId(), paciente.getNombre(), paciente.getApellido(),
                paciente.getDni(), paciente.getFechaAlta(), domicilioDTO);
    }

    public Paciente mapToEntity(PacienteDTO pacienteDTO) {
        Domicilio domicilio = new Domicilio(pacienteDTO.domicilio().id(), pacienteDTO.domicilio().calle(),
                pacienteDTO.domicilio().numero(), pacienteDTO.domicilio().localidad(), pacienteDTO.domicilio().provincia());

        return new Paciente(pacienteDTO.id(), pacienteDTO.nombre(), pacienteDTO.apellido(),
                pacienteDTO.dni(), pacienteDTO.fechaAlta(), domicilio);
    }

    public PacienteDTO guardarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapToEntity(pacienteDTO);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return mapToDTO(pacienteGuardado);
    }

    public Optional<PacienteDTO> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id).map(this::mapToDTO);
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PacienteDTO modificarPaciente(Long id, DataUpdatePaciente dataUpdatePaciente) {
        Paciente pacienteExistente = pacienteRepository.getReferenceById(dataUpdatePaciente.id());
        pacienteExistente.actualizarDatosPaciente(dataUpdatePaciente);
        return mapToDTO(pacienteExistente);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
