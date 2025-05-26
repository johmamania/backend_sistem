package com.backend_sistem.model.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIS_OPCIONES")
public class Opciones {

    @Id
    private Integer idOpcion;

    @Column(length = 1000)
    private String texto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private PregExamen pregunta;

}
