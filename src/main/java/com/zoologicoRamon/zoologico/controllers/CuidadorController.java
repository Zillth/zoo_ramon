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

import com.zoologicoRamon.zoologico.models.Cuidador;
import com.zoologicoRamon.zoologico.services.CuidadorService;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {
    private final CuidadorService cuidadorService;

    public CuidadorController(CuidadorService cuidadorService) {
        this.cuidadorService = cuidadorService;
    }

    @GetMapping
    public ResponseEntity<List<Cuidador>> getAll() {
        List<Cuidador> cuidadores = null;
        try {
            cuidadores = this.cuidadorService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(cuidadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuidador> getAll(@PathVariable("id") int id) {
        Cuidador cuidador = null;
        try {
            cuidador = this.cuidadorService.getById(id);
            if(cuidador.getId_cuidador() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(cuidador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody Cuidador cuidador) {
        try {
            this.cuidadorService.save(cuidador);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody Cuidador cuidador) {
        try {
            this.cuidadorService.update(cuidador);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") int id) {
        try {
            this.cuidadorService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
