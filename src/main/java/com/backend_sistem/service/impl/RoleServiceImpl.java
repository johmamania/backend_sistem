package com.backend_sistem.service.impl;


import com.backend_sistem.model.table.Role;
import com.backend_sistem.repository.IGenericRepo;
import com.backend_sistem.repository.IRoleRepo;
import com.backend_sistem.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ICRUDServiceImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;



    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Role> listRolebyId(String id) {
        return repo.listRolesbyId(id);
    }

    @Override
    public List<Role> listRoles() {
        return repo.listRoles();
    }





}
