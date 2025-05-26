package com.backend_sistem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long idUser;
    private String dni;
    private String username;
    private String fullname;
    private Integer estado;
    private String telefono;
    private List<RoleDTO> roles;

}
