package com.backend_sistem.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PERSONAS")
@Immutable
public class Persona implements Serializable {

    @Id
    @Column (name = "ID", updatable = false, nullable = false )
    private String id;

    @Column( name = "DNI")
    private String dni;

    @Column( name ="APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column( name ="APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column( name ="NOMBRES")
    private String nombres;

    @Column( name ="ESTADO")
    private int estado;




}

