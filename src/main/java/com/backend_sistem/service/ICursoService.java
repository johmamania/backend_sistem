package com.backend_sistem.service;

import com.backend_sistem.model.table.Cursos;
import com.backend_sistem.model.table.NotaFInal;
import com.backend_sistem.model.table.PregExamen;
import com.backend_sistem.model.table.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICursoService extends ICRUDService<Cursos,Integer>{

    List<Cursos> listCursosyId( String username);

    List<Cursos> listCursos();
    List<PregExamen> listbyPreguntasIdCursos(Integer idCurso);

    Page<Cursos>pageCursosAll(String s, Pageable  pageable);

    void saveNotaFinal(NotaFInal notaFInal);


    List<NotaFInal> listNotasByIdCurso(String username,Integer idCurso);
}
/*spring.datasource.url=jdbc:oracle:thin:@//
spring.datasource.username=
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
server.port=8080*/