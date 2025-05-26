package com.backend_sistem.model.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIS_RESPUESTAS_ALUMNO")
public class RespuestasAlumno {

    @Id
    private Integer id;

    @Column
    private Integer idCurso;

    @Column
    private Integer idUsuario;

    @Column
    private Integer idPregunta;

    @Column
    private Integer idRespuesta;

    @Column
    private Date fecha_examen;
}
