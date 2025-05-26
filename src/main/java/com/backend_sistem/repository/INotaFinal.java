package com.backend_sistem.repository;

import com.backend_sistem.model.table.NotaFInal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotaFinal extends IGenericRepo<NotaFInal,Integer> {

    @Query("SELECT MAX(n.intento) FROM NotaFInal n WHERE n.username = :username AND n.id_curso = :idCurso")
    Integer findMaxIntentoByUsernameAndCurso(@Param("username") String username, @Param("idCurso") Integer idCurso);


    @Query("FROM NotaFInal n WHERE n.username = :username AND n.id_curso = :idCurso")
    List<NotaFInal> listNotasByIdCurso( @Param("username") String username,@Param("idCurso") Integer idCurso);

}
