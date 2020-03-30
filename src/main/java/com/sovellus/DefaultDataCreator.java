/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;


package com.sovellus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.io.*;
import java.time.LocalDateTime;

/**
 *
 * @author Niko
 */

// Luokka joka luo käynnistyksessä olioita kantaan demotusta varten
@Configuration
public class DefaultDataCreator {
 
    @Bean
    CommandLineRunner initDataBase(IKiekkoRepository repository) {

    return args -> {
        Kiekko K1 = new Kiekko("Mako3");
        Kiekko K2 = new Kiekko("Teebird");
        
        K1.setTime(LocalDateTime.now());
        K2.setTime(LocalDateTime.now());
        
        repository.save(K1);
        repository.save(K2);
        };
    }

}
