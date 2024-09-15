package com.project.demo.entity;

import com.project.demo.dto.request.DataUpdatePaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaAlta;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    public Paciente(Long id, String nombre, String apellido, String dni, LocalDate fechaAlta, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
    }
    public void actualizarDatosPaciente(DataUpdatePaciente dataUpdatePaciente) {
        this.nombre = dataUpdatePaciente.nombre();
        this.apellido = dataUpdatePaciente.apellido();
        this.dni = dataUpdatePaciente.dni();
        this.fechaAlta = dataUpdatePaciente.fechaAlta();

        // Actualizando domicilio
        if (this.domicilio != null && dataUpdatePaciente.domicilio() != null) {
            this.domicilio.setCalle(dataUpdatePaciente.domicilio().calle());
            this.domicilio.setNumero(dataUpdatePaciente.domicilio().numero());
            this.domicilio.setLocalidad(dataUpdatePaciente.domicilio().localidad());
            this.domicilio.setProvincia(dataUpdatePaciente.domicilio().provincia());
        }
    }
}
