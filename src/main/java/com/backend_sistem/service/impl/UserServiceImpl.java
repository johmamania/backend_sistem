package com.backend_sistem.service.impl;

import com.backend_sistem.dto.ChangePassword;
import com.backend_sistem.model.table.Cursos;
import com.backend_sistem.model.table.Role;
import com.backend_sistem.model.table.User;
import com.backend_sistem.repository.ICursosRepo;
import com.backend_sistem.repository.IGenericRepo;
import com.backend_sistem.repository.IRoleRepo;
import com.backend_sistem.repository.IUserRepo;
import com.backend_sistem.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl extends ICRUDServiceImpl<User, Long> implements IUserService {

    private final IUserRepo repo;
    private final IRoleRepo repoRol;
    private final ICursosRepo repoCursos;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected IGenericRepo<User, Long> getRepo() {
        return repo;
    }


    @Override
    public void guardarUsuarioConRoles(User user, List<Integer> idsRoles) {

        // Cifra la contraseña antes de guardarla
        //System.out.println(user.getUsername() + user.getDni());
        String encryptedPassword = passwordEncoder.encode(user.getUsername() + user.getDni());
        user.setPassword(encryptedPassword);
        List<Role> roles = repoRol.findAllById(idsRoles);
        user.setRoles(roles);
        user.setEstado(0);
        repo.save(user);
    }

    @Override
    public void actualizarUsuarioConRoles(Long userId, User updatedUser, List<Integer> idsRoles) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String encryptedPassword = passwordEncoder.encode(user.getUsername() + user.getDni());

        user.setDni(updatedUser.getDni());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(encryptedPassword);
        user.setFullname(updatedUser.getFullname());
        user.setTelefono(updatedUser.getTelefono());
        List<Role> roles = repoRol.findAllById(idsRoles);
        user.setRoles(roles);

        repo.save(user);
    }


    @Override
    public void actualizarUsuarioCursos(Long userId, User updatedUser, List<Integer> idsCursos) {
            User user = repo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String encryptedPassword = passwordEncoder.encode(user.getUsername() + user.getDni());

            List<Cursos> cursos = repoCursos.findAllById(idsCursos);
            user.setCursos(cursos);

            repo.save(user);
    }

    @Override
    public User buscarporUser(String username) {
        return repo.findByUsername(username);
    }


    @Override
    public void changeEstado(String username, Integer estado) {

        User usuario = repo.findByUsername(username);

        if (usuario != null) {
            usuario.setEstado(estado);
            repo.save(usuario);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado : " + username);
        }
    }

    @Override
    public void changePassword(ChangePassword changePassword) {

      //  if(changePassword.getNewPassword() != changePassword.getConfirmNewPasword()) {
      //      throw new EntityNotFoundException("Las Contraseñas No Coinciden : " + changePassword.getUsername());
     //   }

       // System.out.println(changePassword);
        User usuario = repo.findByUsername(changePassword.getUsername());
        if (usuario != null) {
            String encryptedPassword = passwordEncoder.encode(changePassword.getNewPassword());
            usuario.setPassword(encryptedPassword);
            usuario.setEstado(1);
            repo.save(usuario);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado : " + changePassword.getUsername());
        }
    }

    @Override
    public Page<User> filteruserslist(String searchParam, Pageable pageable) {
        // Limitar el tamaño de la página a 300
        //  Pageable limitedPageable = PageRequest.of(pageable.getPageNumber(), Math.min(pageable.getPageSize(), 300), pageable.getSort());
        return repo.pageListUsers(searchParam, pageable);

    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {

        String contextSessionUser = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional = repo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getUsername().equals(contextSessionUser)) {
                throw new EntityNotFoundException("No puedes eliminarte a ti mismo");
            } else {
                repo.delete(user);
            }
        } else {
            throw new EntityNotFoundException("Usuario no encontrado con id : " + userId);
        }
    }

    @Override
    public void changeRoles(String username, List<Integer> rolesIds) {

        User user = repo.findByUsername(username);
        user.getRoles().clear();

        for (Integer roleId : rolesIds) {
            Role role = new Role();
            role.setIdRole(roleId);
            user.getRoles().add(role);
        }
        repo.save(user);
    }

    @Override
    public void changeCursosUers(String username, List<Integer> idCursos) {
        User user = repo.findByUsername(username);
        user.getCursos().clear();
        for (Integer idCurso : idCursos) {
            Cursos cursos = new Cursos();
            cursos.setIdCurso(idCurso);
            user.getCursos().add(cursos);
        }
        repo.save(user);
    }


}
