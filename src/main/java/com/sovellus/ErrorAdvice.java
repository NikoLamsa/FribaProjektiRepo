/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sovellus;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author Niko
 */

// Palauttaa virheilmoituksen virhetilanteissa
@ControllerAdvice
public class ErrorAdvice {

    @ResponseBody
    @ExceptionHandler(RestErrors.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String errorMessage(RestErrors error) {
            return error.getMessage();
        }
    

}
