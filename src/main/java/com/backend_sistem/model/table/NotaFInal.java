package com.backend_sistem.model.table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SIS_NOTA FINAL")
public class NotaFInal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;
    private Integer id_curso;
    @Column
    private int intento;

    @Column
    private int correctas;
    private int incorrectas;
    private Date fecha_reg;
}
