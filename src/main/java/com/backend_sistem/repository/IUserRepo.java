package com.backend_sistem.repository;

import com.backend_sistem.model.table.User;
import com.backend_sistem.model.view.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepo extends  IGenericRepo<User, Long> {

    User findByUsername(String username);

    @Query("from Persona where id =:cip")
    Persona buscarporCipUserRegister(String cip);

    @Query(
            value = "FROM User c WHERE UPPER(c.username) LIKE UPPER(CONCAT('%', :searchParam,'%')) OR UPPER(c.dni) LIKE UPPER(CONCAT('%',:searchParam,'%')) OR UPPER(c.fullname) LIKE UPPER(CONCAT('%', :searchParam,'%')) " )
    Page<User> pageListUsers(@Param("searchParam") String searchParam, Pageable pageable);

}
