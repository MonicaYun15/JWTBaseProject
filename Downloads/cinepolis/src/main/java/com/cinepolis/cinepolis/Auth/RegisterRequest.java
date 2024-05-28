/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinepolis.cinepolis.Auth;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author admin
 */
@Data
@Builder
@AllArgsConstructor  //Equivalente a generar un constructor con los argumentos 
@NoArgsConstructor
public class RegisterRequest {
    
    private String username;
    
    private String password;
    
    private String nombre;

    private String apellidos;
    
    private Date fechaNacimiento;
    
    private String codigoPostal;
    
    private String municipio;
    
    private String ciudad;

    private String pais;
 
    private String email;

    private String telefono;
}
