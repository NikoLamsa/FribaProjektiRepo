/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sovellus;


/**
 *
 * @author Niko
 */

//Luodaan oma Exception jolle voidaan määrittää viesti
//ja ErrorAdvice luokka käyttää
public class RestErrors extends RuntimeException {
    
    RestErrors (String message) {
        super(message);
                
    }
}
