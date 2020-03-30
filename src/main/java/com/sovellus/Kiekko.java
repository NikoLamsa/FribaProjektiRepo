/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sovellus;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
 *
 * @author Niko
 */

// Olio joita tallennetaan
@Entity
public class Kiekko {

    private @Id @GeneratedValue Long id;
    private String name;
    private LocalDateTime time;

public Kiekko() {}
public Kiekko(String name) {
    this.name = name;
    //time = LocalDateTime.now();    
}

// Getters
public Long getId() {
    return id;
}
public String getName() {
    return name;
}
public LocalDateTime getTime() {
    return time;
}
public void setTime(LocalDateTime time) {
	this.time = time;
}

// Setters
public void setId(Long id) {
    this.id = id;
}

public void setName(String name) {
    this.name = name;
}

}
