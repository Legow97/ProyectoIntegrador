package com.project.demo.service;
import com.project.demo.dto.request.DataUpdateTurno;
import com.project.demo.dto.request.OdontologoDTO;
import com.project.demo.dto.request.PacienteDTO;
import com.project.demo.dto.request.TurnoDTO;
import com.project.demo.entity.Domicilio;
import com.project.demo.entity.Odontologo;
import com.project.demo.entity.Paciente;
import com.project.demo.entity.Turno;
import com.project.demo.repository.IOdontologoRepository;
import com.project.demo.repository.IPacienteRepository;
import com.project.demo.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    private TurnoDTO mapToDTO(Turno turno) {
        return new TurnoDTO(turno.getId(), turno.getFecha(), turno.getOdontologo().getId(), turno.getPaciente().getId());
    }

    private Turno mapToEntity(TurnoDTO turnoDTO) {
        PacienteDTO pacienteDTO = pacienteService.buscarPacientePorId(turnoDTO.pacienteId()).orElseThrow();
        Paciente paciente = pacienteService.mapToEntity(pacienteDTO);
        OdontologoDTO odontologoDTO = odontologoService.buscarOdontologoPorId(turnoDTO.odontologoId()).orElseThrow();
        Odontologo odontologo = odontologoService.mapToEntity(odontologoDTO);

        return new Turno(turnoDTO.id(),turnoDTO.fecha(), paciente, odontologo);
    }

    public TurnoDTO registrarTurno(Turno turno) {
        Turno turnoGuardado = turnoRepository.save(turno);
        return mapToDTO(turnoGuardado);
    }

    public TurnoDTO modificarTurno(Long id, DataUpdateTurno dataUpdateTurno) {
        Turno turnoExistente = turnoRepository.getReferenceById(id);

        Paciente paciente =pacienteService.mapToEntity(pacienteService.buscarPacientePorId(dataUpdateTurno.pacienteId()).orElseThrow());
        Odontologo odontologo = odontologoService.mapToEntity(odontologoService.buscarOdontologoPorId(dataUpdateTurno.odontologoId()).orElseThrow());

        turnoExistente.actualizarTurno(dataUpdateTurno.fecha(),paciente,odontologo);

        return mapToDTO(turnoExistente);
    }

    public List<TurnoDTO> listarTurnos() {
        return turnoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<TurnoDTO> buscarTurnoPorId(Long id) {

        return turnoRepository.findById(id).map(this::mapToDTO);
    }

    public void eliminarTurno(Long id) {

        turnoRepository.deleteById(id);
    }
}

//    public TurnoDTO registrarTurno(Turno turno){
//        Turno turnoGuardado= turnoRepository.save(turno);
//        return turnoATurnoDto(turnoGuardado);
//    }
//
//    public TurnoDTO turnoATurnoDto(Turno turno){
//        TurnoDTO dto= new TurnoDTO();
//        dto.setId(turno.getId());
//        dto.setFecha(turno.getFecha());
//        dto.setOdontologoId(turno.getOdontologo().getId());
//        dto.setPacienteId(turno.getPaciente().getId());
//        return dto;
//    }
//}