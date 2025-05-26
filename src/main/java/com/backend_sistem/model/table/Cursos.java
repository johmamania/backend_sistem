package com.backend_sistem.model.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SIS_CURSOS")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curso_seq")
    @SequenceGenerator(name = "curso_seq", sequenceName = "SEQ_SIS_CURSOS", allocationSize = 1)
    private Integer idCurso;

    @Column
    private String name;

    @Column
    private String description;




}
