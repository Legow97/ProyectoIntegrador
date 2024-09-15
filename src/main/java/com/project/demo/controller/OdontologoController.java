package com.project.demo.controller;

import com.project.demo.dto.request.DataUpdateOdontologo;
import com.project.demo.dto.request.OdontologoDTO;
import com.project.demo.entity.Odontologo;
import com.project.demo.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/agregar")
    public ResponseEntity<OdontologoDTO> registerOdontologo(@RequestBody OdontologoDTO odontologoDTO){
        OdontologoDTO nuevoOdontologo = odontologoService.guardarOdontologo(odontologoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoOdontologo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> getOdontologo(@PathVariable Long id){

        Optional<OdontologoDTO> odontologoOptional = odontologoService.buscarOdontologoPorId(id); // Retorna un DTO

        if (odontologoOptional.isPresent()) {
            OdontologoDTO odontologoDTO = odontologoOptional.get();
            return ResponseEntity.ok(odontologoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDTO> modificaOdontologo(@PathVariable Long id, @RequestBody DataUpdateOdontologo dataUpdateOdontologo){
        Optional<OdontologoDTO> odontologoOptional = odontologoService.buscarOdontologoPorId(id);
        if(odontologoOptional.isPresent()){
            OdontologoDTO oDTO = odontologoService.modificarOdontologo(id,dataUpdateOdontologo);
            return ResponseEntity.ok(oDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Optional<OdontologoDTO> odontologoBuscadoDTO =odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscadoDTO.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo Eliminado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> listarOdontologos() {
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }
}

