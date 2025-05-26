package com.backend_sistem.model.table;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIS_PREGUNTAS")
public class PregExamen {


    @Id
    private Integer idPregunta;

    @Column(name="id_curso")
    private Integer idCurso;

    private Integer orden;

    @Column(length = 1000)
    private String namePregunta;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opciones> opciones = new ArrayList<>();

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuestas> respuestas = new ArrayList<>();

}
