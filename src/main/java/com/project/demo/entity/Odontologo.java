package com.project.demo.entity;

import com.project.demo.dto.request.DataUpdateOdontologo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String nroMatricula;


    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Odontologo(Long id, String nombre, String apellido, String nroMatricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroMatricula = nroMatricula;
    }


    public void actualizarDatosOdontologo(DataUpdateOdontologo dataUpdateOdontologo){
        this.nombre = dataUpdateOdontologo.nombre();
        this.apellido = dataUpdateOdontologo.apellido();
        this.nroMatricula = dataUpdateOdontologo.nroMatricula();
    }

}
