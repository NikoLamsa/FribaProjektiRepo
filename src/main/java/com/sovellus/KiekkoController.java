/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Niko
 */

package com.sovellus;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

@RestController
public class KiekkoController {
  
    private IKiekkoRepository repository;

    private int nameMaxLength = 64, nameMinLength = 4;

    //vaatii repositoryn
    public KiekkoController(IKiekkoRepository repository) {
    
        this.repository = repository;
    }

    
    //POST, GET, PUT, DELETE toiminnot
    
    // Listaa kaikki, palauttaa tyhjän listan jos mitään ei löydy
    @GetMapping ("/Kiekot")
    List<Kiekko> getALL() {
        return repository.findAll();
    }
    
    // Listaa kaikki joilla haettu nimi, palauttaa tyhjän listan
    // jos mitään ei löydy
    @GetMapping ("/Kiekot/Nimi/{name}")
    List<Kiekko> getByName(@PathVariable String name)  {
        
        return repository.findByName(name);
      }
    
    // Listaa yhden jolla haettu id
    @GetMapping ("/Kiekot/ID/{id}")
    Kiekko getOneById(@PathVariable Long id) throws RuntimeException {
    	
    	Optional<Kiekko> k = repository.findById(id);
    	if (k.isPresent()) 
    		return k.get();
    	else throw new RestErrors("Kohdetta ei ole!");   			
    			
    }

    // tarkistaa nimen pituuden ja tallentaa sitten olion
    // repositoryyn ja palauttaa tiedot tallennuksesta tai virheen
    @PostMapping (path = "/Kiekot", consumes = "application/json")
    Kiekko postKiekko(@RequestBody Kiekko newKiekko) throws RuntimeException {
        
        
        if (!checkName(newKiekko.getName()))

        throw new RestErrors("Virheellinen nimi");
        else {                	
        	newKiekko.setTime(LocalDateTime.now());
        	return repository.save(newKiekko);
        }
    
    }
   
   //tarkistaa alkuun nimen pituuden, etsii haetun olion id perusteella,
   // jos löytyy, korvataan löydetyn olion tiedot uusilla ja tallennetaan takaisin
   @PutMapping ("/Kiekot/{id}")
   Kiekko putKiekko (@RequestBody Kiekko newKiekko, @PathVariable Long id) 
   throws RuntimeException {
        
        if (!checkName(newKiekko.getName()))
        throw new RestErrors("Virheellinen nimi!");

        Optional<Kiekko> K = repository.findById(id);

        if (K.isPresent()) {
                K.get().setName(newKiekko.getName());
                K.get().setId(id);
                return repository.save(K.get()); 
            }

        else throw new RestErrors("Kohdetta ei ole!");
    }

   // etsii olion Id perusteella ja poistetaan jos lÃ¶ytyy
    @DeleteMapping ("/Kiekot/{id}")
    void deleteKiekko(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // metodi nimentarkistusta varten
    // Palauttaa false mikÃ¤li nimi ei tÃ¤ytÃ¤ kriteerejä
    boolean checkName(String name) {
        if (name.length() < nameMinLength || name.length() > nameMaxLength)
        return false;

        else return true;
    }
    
}
