package com.diagnosticos.Vitalia.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    private String especialidad;
    private String registroProfesional;

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    // Agregado para compatibilidad con getId()
    public Long getId() {
        return idMedico;
    }
}
