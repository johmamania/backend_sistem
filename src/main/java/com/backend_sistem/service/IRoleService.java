package com.backend_sistem.service;

import com.backend_sistem.model.table.Role;


import java.util.List;

public interface IRoleService extends ICRUDService<Role, Integer>{

    List<Role> listRolebyId(String idUser);

    List<Role> listRoles();

}
