package com.backend_sistem.repository;

import com.backend_sistem.model.table.Cursos;
import com.backend_sistem.model.table.Role;
import com.backend_sistem.model.table.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursosRepo extends IGenericRepo<Cursos,Integer>{


    @Query(value = """
    SELECT c.id_curso, c.name, c.description
    FROM sis_user u
    JOIN sis_user_cursos uc ON u.id_user = uc.id_user
    JOIN sis_cursos c ON uc.id_curso = c.id_curso
    WHERE u.username = :username
    ORDER BY c.id_curso ASC
    """, nativeQuery = true)
    List<Cursos> listCursosByDni(@Param("username") String username);


    @Query(value = "FROM Cursos R ORDER BY R.idCurso ASC")
    List<Cursos> listCursos();

    @Query(
            value = "FROM Cursos c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :s,'%')) OR UPPER(c.description) LIKE UPPER(CONCAT('%',:s,'%')) " )
    Page<Cursos> pageCursosAll(@Param("s") String s, Pageable pageable);

}
