package com.backend_sistem.controller;

import com.backend_sistem.dto.UsuarioDTO;
import com.backend_sistem.model.table.Cursos;
import com.backend_sistem.model.table.NotaFInal;
import com.backend_sistem.model.table.PregExamen;
import com.backend_sistem.model.table.Role;
import com.backend_sistem.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursosController {

    private final ICursoService service;

    @GetMapping("/list-all")
    public ResponseEntity<List<Cursos>> findAllRoles() {
        List<Cursos> c = service.listCursos();
        return ResponseEntity.ok(c);
    }

    @GetMapping("/list-cursos-user/{username}")
    public ResponseEntity<List<Cursos>> findbyIdCursos(@PathVariable String username) {
        List<Cursos> c = service.listCursosyId(username);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/list-preguntas/{idCurso}")
    public ResponseEntity<List<PregExamen>> findbyPreguntasIdCursos(@PathVariable Integer idCurso) {
        List<PregExamen> c = service.listbyPreguntasIdCursos(idCurso);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cursos>> pageCursos(
            @RequestParam(value = "s", defaultValue = "") String s,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Cursos> p = service.pageCursosAll(s, pageable);
        return ResponseEntity.ok(p);
    }


    @GetMapping("/id/{idCurso}")
    public ResponseEntity<Cursos> findbyIdCursos(@PathVariable Integer idCurso) {
        Cursos c = service.findById(idCurso);
        return ResponseEntity.ok(c);
    }

    @PostMapping("/save")
    public ResponseEntity<?>save(@RequestBody Cursos curso) {
        service.save(curso);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/update/{idCurso}")
    public ResponseEntity<?> put(@PathVariable Integer idCurso , @RequestBody Cursos curso) {
        Cursos c = service.findById(idCurso);
        c.setName(curso.getName());
        c.setDescription(curso.getDescription());
        service.update(c);
        return ResponseEntity.ok(HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{idCurso}")
    public ResponseEntity<?> delete(@PathVariable Integer idCurso) {
        service.delete(idCurso);
       return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/save-nota")
    public ResponseEntity<?>saveNota(@RequestBody NotaFInal n) {

        service.saveNotaFinal(n);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list-all-notas/{idCurso}")
    public ResponseEntity<List<NotaFInal>> listNotasUserIdCurso(@PathVariable Integer idCurso) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
        List<NotaFInal> c = service.listNotasByIdCurso(username,idCurso);
        return ResponseEntity.ok(c);
    }
}
