package com.backend_sistem.repository;

import com.backend_sistem.model.table.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoleRepo extends IGenericRepo<Role,Integer>{

    @Query(value = """
                    SELECT r.id_role, r.name, r.description
                    FROM sis_user ud
                    JOIN sis_user_role ur ON ud.id_user = ur.id_user
                    JOIN role r ON ur.id_role = r.id_role
                    WHERE ud.cip = :id ORDER BY r.id_role ASC
                    """, nativeQuery = true)
    List<Role> listRolesbyId(@Param("id") String id);

    @Query(value = "FROM Role R ORDER BY R.idRole ASC")
    List<Role> listRoles();

}
