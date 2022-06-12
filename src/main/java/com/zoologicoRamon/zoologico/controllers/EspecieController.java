package com.zoologicoRamon.zoologico.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoologicoRamon.zoologico.models.Especie;
import com.zoologicoRamon.zoologico.services.EspecieService;

@RestController
@RequestMapping("/especie")
public class EspecieController {
    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public ResponseEntity<List<Especie>> getAll() {
        List<Especie> especies = null;
        try {
            especies = this.especieService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(especies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> getAll(@PathVariable("id") int id) {
        Especie especie = null;
        try {
            especie = this.especieService.getById(id);
            if(especie.getId_especie() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(especie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody Especie especie) {
        try {
            this.especieService.save(especie);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody Especie especie) {
        try {
            this.especieService.update(especie);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") int id) {
        try {
            this.especieService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
