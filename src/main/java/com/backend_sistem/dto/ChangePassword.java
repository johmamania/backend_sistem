package com.backend_sistem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

    private String username;
    private String newPassword;
    private String confirmNewPasword;
   }
