package com.backend_sistem.controller;


import com.backend_sistem.model.table.User;

import com.backend_sistem.exception.CustomErrorResponse;
import com.backend_sistem.security.JwtRequest;
import com.backend_sistem.security.JwtResponse;
import com.backend_sistem.security.JwtTokenUtil;
import com.backend_sistem.security.JwtUserDetailsService;
import com.backend_sistem.service.IUserService;
import com.backend_sistem.service.impl.RecaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final RecaptchaService captchaService;
    private final IUserService service;


    @PostMapping("/bd")
    public ResponseEntity<?> login(@RequestBody JwtRequest req) {
        Map<String, Object> response = new HashMap<>();
        try {
          //  if (!captchaService.verify(req.getToken())) {
          //      return new ResponseEntity<>(new CustomErrorResponse(LocalDateTime.now(), "reCAPTCHA INVÁLIDO", "/login"), HttpStatus.UNAUTHORIZED);
           // }
         //   final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

           // System.out.println(req.getUsername());
         //   System.out.println(req.getPassword());
            if (!userDetailsService.loginBybd(req.getUsername(), req.getPassword())) {
             return new ResponseEntity<>(new CustomErrorResponse(LocalDateTime.now(), "Usuario o Clave incorrectas", "/login"), HttpStatus.UNAUTHORIZED);
            }
            User user = service.buscarporUser(req.getUsername());
            final UserDetails userDetail = userDetailsService.loadUserByUsername(user.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetail);
            return ResponseEntity.ok(new JwtResponse(token));
           // return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new CustomErrorResponse(LocalDateTime.now() ,e.getMessage(), "/login"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse(LocalDateTime.now(), "Error en el servidor", "/login"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

