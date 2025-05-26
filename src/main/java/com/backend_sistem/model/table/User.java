package com.backend_sistem.model.table;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SIS_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PUSUARIOS_ID")
    @SequenceGenerator(name = "SEQ_PUSUARIOS_ID", allocationSize = 1, sequenceName = "SEQ_PUSUARIOS_ID")
    private Long idUser;

    @Column( length = 8)
    private String dni;

    @Column(length = 150)
    private String fullname;

    @Column(length = 150)
    private String username;

    @Column(length = 150)
    private String password;

    @Column(length = 9)
    private String telefono;

    @Column(length = 2 )
    private Integer estado; // 1 activo 2 desactivado

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sis_user_role",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sis_user_cursos",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "id_curso", referencedColumnName = "idCurso"))
    private List<Cursos> cursos;


}


