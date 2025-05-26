package com.backend_sistem.service;



import com.backend_sistem.dto.ChangePassword;
import com.backend_sistem.model.table.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService extends ICRUDService<User, Long>{

    void guardarUsuarioConRoles(User user, List<Integer> idsRoles);

    void actualizarUsuarioConRoles(Long userId, User updatedUser, List<Integer> idsRoles);
    void actualizarUsuarioCursos(Long userId, User updatedUser, List<Integer> idsCursos);

    User buscarporUser(String username);


    void changeEstado(String username, Integer estado);
    void changePassword(ChangePassword changePassword);


    Page<User> filteruserslist(String searchParam, Pageable pageable);
    void deleteUser(Long id);

    void changeRoles(String cip, List<Integer> rolesIds);
    void changeCursosUers(String username, List<Integer> idCursos);



}
