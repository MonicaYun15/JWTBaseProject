/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinepolis.cinepolis.Demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
//Metodo protegido
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DemoController {
    
    @PostMapping("/demo")
    public String welcome(){
       return "welcome from secure endpoint"; 
    }
    
    
}
