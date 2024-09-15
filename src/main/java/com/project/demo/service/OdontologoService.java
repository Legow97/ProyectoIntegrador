package com.project.demo.service;

import com.project.demo.dto.request.DataUpdateOdontologo;
import com.project.demo.dto.request.OdontologoDTO;
import com.project.demo.entity.Odontologo;
import com.project.demo.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;

    public OdontologoDTO mapToDTO(Odontologo odontologo) {
        return new OdontologoDTO(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getNroMatricula());
    }

    public Odontologo mapToEntity(OdontologoDTO odontologoDTO) {
        return new Odontologo(odontologoDTO.id(), odontologoDTO.nombre(), odontologoDTO.apellido(), odontologoDTO.nroMatricula());
    }

    public OdontologoDTO guardarOdontologo(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapToEntity(odontologoDTO);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        return mapToDTO(odontologoGuardado);
    }

    public Optional<OdontologoDTO> buscarOdontologoPorId(Long id) {
        return odontologoRepository.findById(id)
                .map(odontologo -> new OdontologoDTO(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getNroMatricula()));
    }


    public OdontologoDTO modificarOdontologo(Long id, DataUpdateOdontologo dataUpdateOdontologo){
        Odontologo odontologo1 = odontologoRepository.getReferenceById(dataUpdateOdontologo.id());
        odontologo1.actualizarDatosOdontologo(dataUpdateOdontologo);
        return mapToDTO(odontologo1);
    }

    public void eliminarOdontologo(Long id) {

        odontologoRepository.deleteById(id);
    }

    public List<OdontologoDTO> listarOdontologos() {
        return odontologoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
