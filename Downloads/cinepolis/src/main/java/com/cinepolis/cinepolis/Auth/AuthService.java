/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinepolis.cinepolis.Auth;

import com.cinepolis.cinepolis.Jwt.JwtService;
import com.cinepolis.cinepolis.User.Role;
import com.cinepolis.cinepolis.User.User;
import com.cinepolis.cinepolis.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author admin
 */
@Service
@RequiredArgsConstructor
class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .nombre(request.getNombre())
            .apellidos(request.getApellidos())
            .fechaNacimiento(request.getFechaNacimiento())
            .codigoPostal(request.getCodigoPostal())
            .municipio(request.getMunicipio())
            .ciudad(request.getCiudad())
            .pais(request.getPais())
            .email(request.getEmail())
            .telefono(request.getTelefono())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }
}
