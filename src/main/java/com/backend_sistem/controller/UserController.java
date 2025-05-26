package com.backend_sistem.controller;


import com.backend_sistem.dto.ChangePassword;
import com.backend_sistem.dto.MsgResp;
import com.backend_sistem.dto.UsuarioDTO;
import com.backend_sistem.exception.CustomErrorResponse;
import com.backend_sistem.model.table.Role;
import com.backend_sistem.model.table.User;
import com.backend_sistem.service.IRoleService;
import com.backend_sistem.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.backend_sistem.util.constants.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    private final IRoleService roleService;
    private final ModelMapper modelMapper;


    @PostMapping()
    public ResponseEntity<?> crearUsuarioConRoles(@RequestBody User user, @RequestParam List<Integer> idsRoles) {

        User verificarUsuario = service.buscarporUser(user.getUsername());



        try {
            if (verificarUsuario == null) {

                service.guardarUsuarioConRoles(user, idsRoles);
                return ResponseEntity.ok(new MsgResp(MSG_USUARIO_CREATED));

            } else {
                CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO_REG, "/users");
                return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> actualizarUsuarioConRoles(@PathVariable Long userId, @RequestBody User user, @RequestParam List<Integer> idsRoles) {
        try {
            // Verificar si el usuario existe
            User verificarUsuario = service.buscarporUser(user.getUsername());
            if (verificarUsuario == null || verificarUsuario.getIdUser().equals(userId)) {

                service.actualizarUsuarioConRoles(userId, user, idsRoles);
                return ResponseEntity.ok(new MsgResp(MSG_USUARIO_UPDATE));
            } else {
                CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO_REG, "/users");
                return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/asignar-cursos/{userId}")
    public ResponseEntity<?> actualizarUsuarioAsignaCcurso(@PathVariable Long userId, @RequestBody User user, @RequestParam List<Integer> idsRoles) {
        try {
            // Verificar si el usuario existe
            User verificarUsuario = service.buscarporUser(user.getUsername());
            if (verificarUsuario == null || verificarUsuario.getIdUser().equals(userId)) {

                service.actualizarUsuarioCursos(userId, user, idsRoles);
                return ResponseEntity.ok(new MsgResp(MSG_USUARIO_UPDATE));
            } else {
                CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO_REG, "/users");
                return new ResponseEntity<>(customErrorResponse, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable("username") String username) throws Exception {
        User obj = service.buscarporUser(username);

        if (obj == null) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO_NO_EXIST, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return ResponseEntity.ok(convertToDto(obj));
        }
    }

    @PutMapping("/roles/{username}")
    public ResponseEntity<?> changeRoles(@PathVariable String username, @RequestBody List<Integer> rolesIds) {
        try {
            service.changeRoles(username, rolesIds);
            return ResponseEntity.ok(new MsgResp(MSG_USUARIO_CREATED));
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_ROLES, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/estado/{username}/{estado}")
    public ResponseEntity<?> changeEstado(@PathVariable("username") String username, @PathVariable("estado") Integer estado) {
        try {
            service.changeEstado(username, estado);
            return ResponseEntity.ok(new MsgResp(MSG_USUARIO_OK));
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USUARIO, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword( @RequestBody ChangePassword changePassword) {
        try {
            service.changePassword(changePassword);
            return ResponseEntity.ok(new MsgResp(MSG_USER_CHANGE_PASSWORD));
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_USER_CHANGE_PASSWORD, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> roles = roleService.listRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<List<Role>> findbyIdRoles(@PathVariable("id") String id) {
        List<Role> roles = roleService.listRolebyId(id);
        return ResponseEntity.ok(roles);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<UsuarioDTO>> filteruserslist(
            @RequestParam(value = "s", defaultValue = "") String searchParam,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {


        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UsuarioDTO> list = service.filteruserslist(searchParam, pageable).map(this::convertToDto);

        return ResponseEntity.ok(list);
    }

    //actualizar cursos user
    @PutMapping("/cursos-update-user/{username}")
    public ResponseEntity<?> changeCursosUser(@PathVariable String username, @RequestBody List<Integer> idCursos) {
        try {
            service.changeCursosUers(username, idCursos);
            return ResponseEntity.ok(new MsgResp(MSG_CURSO_UPDATE));
        } catch (Exception e) {
            CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), MSG_ERROR_CURSOS, "/users");
            return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//eliminar usuario por cip
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    private UsuarioDTO convertToDto(User obj) {

        return modelMapper.map(obj, UsuarioDTO.class);
    }

   // private PersonaDTO convertToDtoPersona(Persona obj) {        return modelMapper.map(obj, PersonaDTO.class);

    private User convertToEntity(UsuarioDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}

