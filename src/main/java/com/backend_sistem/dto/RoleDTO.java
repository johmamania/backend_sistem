package com.backend_sistem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Integer idRole;

    @NotNull
    private String name;

    @NotNull
    private String description;
}