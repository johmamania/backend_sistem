package com.backend_sistem.repository;

import com.backend_sistem.model.table.PregExamen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPregExamenRepo extends IGenericRepo<PregExamen,Integer>{

    @Query("from PregExamen p where p.idCurso=:idCurso")
    List<PregExamen> findByIdCurso(Integer idCurso);




}
