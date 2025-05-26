package com.backend_sistem.service.impl;

import com.backend_sistem.model.table.Cursos;
import com.backend_sistem.model.table.NotaFInal;
import com.backend_sistem.model.table.PregExamen;
import com.backend_sistem.model.table.User;
import com.backend_sistem.repository.*;
import com.backend_sistem.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends ICRUDServiceImpl<Cursos,Integer> implements ICursoService {

    private final ICursosRepo repo;
    private final IPregExamenRepo repoExamen;
    private final INotaFinal reponota;
    @Override
    protected IGenericRepo<Cursos, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Cursos> listCursosyId(String username ) {
        return repo.listCursosByDni(username);
    }

    @Override
    public List<Cursos> listCursos() {
        return repo.listCursos();
    }

    @Override
    public List<PregExamen> listbyPreguntasIdCursos(Integer idCurso) {
        return repoExamen.findByIdCurso(idCurso);
    }

    @Override
    public Page<Cursos> pageCursosAll(String s, Pageable pageable) {
        return repo.pageCursosAll(s, pageable);
    }


    @Override
    public void saveNotaFinal(NotaFInal n) {
        String userlog =SecurityContextHolder.getContext().getAuthentication().getName();
        // Obtener el próximo número de intento
        int nextIntento = getNextIntentoForUserAndCurso(userlog, n.getId_curso());


        // Setear datos antes de guardar
        n.setIntento(nextIntento);
        n.setUsername(userlog);
        n.setFecha_reg(new Date());
        reponota.save(n);
    }

    @Override
    public List<NotaFInal> listNotasByIdCurso(String username, Integer idCurso) {
        return reponota.listNotasByIdCurso(username,idCurso);
    }

    public int getNextIntentoForUserAndCurso(String username, Integer idCurso) {
        Integer maxIntento = reponota.findMaxIntentoByUsernameAndCurso(username, idCurso);

        return (maxIntento == null) ? 1 : maxIntento + 1;
    }


}
