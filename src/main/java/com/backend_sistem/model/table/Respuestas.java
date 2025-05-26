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
@Table(name = "SIS_RESPUESTAS")
public class Respuestas {

    @Id
    private Integer idRespuesta;

   // @Column
   // private Integer idPregunta;//
   private char orden;
    @Column(length = 1000)
    private String texto_respuesta;

    @Column
    private boolean es_correcta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private PregExamen pregunta;

}
