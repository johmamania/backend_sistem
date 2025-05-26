package com.backend_sistem.security;
import com.backend_sistem.model.table.User;
import com.backend_sistem.repository.IUserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("USUARIO NO ENCONTRADO: " + username);
        }
        if (user.getEstado() == 2) {
            throw new UsernameNotFoundException("USUARIO DESHABILITADO");
        }

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> roles.add(new SimpleGrantedAuthority(rol.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword() ,roles);
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean loginBybd(String username, String password) {
        User user = repo.findByUsername(username);
        if (user != null) {
            // Comparar la contraseña ingresada con la guardada en la base de datos
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
    /*
    public boolean loginBybd(String username, String password) throws Exception {

     //   User oUsuario;
        ADImpl userAD = new ADImpl();
        try {
            if (userAD.login(username, password)) {
             //   User oUsuario = repo.findOneByUsername(username);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }*/
}
