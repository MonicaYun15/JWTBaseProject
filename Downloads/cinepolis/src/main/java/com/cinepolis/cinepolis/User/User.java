/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinepolis.cinepolis.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author admin
 */
@Data   //Equivalente a generar getters y setters con lombok
@Builder
@AllArgsConstructor  //Equivalente a generar un constructor con los argumentos 
@NoArgsConstructor  // equivalente a generar un contructor vacio
@Table(name = "usuarios")
@Entity
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name ="username", unique = true, nullable = false)
    private String username;
    
    @Column(name ="password", nullable = false)
    private String password;
    
    @Column(name ="nombre", nullable = false)
    private String nombre;
    
    @Column(name ="apellidos", nullable = false)
    private String apellidos;
    
    @Column(name ="fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(name ="codigoPostal", nullable = false)
    private String codigoPostal;
    
    @Column(name ="municipio", nullable = false)
    private String municipio;
    
    @Column(name ="ciudad", nullable = false)
    private String ciudad;
    
    @Column(name ="pais", nullable = false)
    private String pais;
    
    @Column(name ="email", unique = true, nullable = false)
    private String email;
    
    @Column(name ="telefono", unique = true, nullable = false)
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

  

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
}
