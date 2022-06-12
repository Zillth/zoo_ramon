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

import com.zoologicoRamon.zoologico.models.Ecosistema;
import com.zoologicoRamon.zoologico.services.EcosistemaService;

@RestController
@RequestMapping("/ecosistema")
public class EcosistemaController {
    private final EcosistemaService ecosistemaService;

    public EcosistemaController(EcosistemaService ecosistemaService) {
        this.ecosistemaService = ecosistemaService;
    }

    @GetMapping
    public ResponseEntity<List<Ecosistema>> getAll() {
        List<Ecosistema> ecosistemas = null;
        try {
            ecosistemas = this.ecosistemaService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(ecosistemas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ecosistema> getAll(@PathVariable("id") int id) {
        Ecosistema ecosistema = null;
        try {
            ecosistema = this.ecosistemaService.getById(id);
            if(ecosistema.getId_ecosistema() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(ecosistema, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody Ecosistema ecosistema) {
        try {
            this.ecosistemaService.save(ecosistema);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody Ecosistema ecosistema) {
        try {
            this.ecosistemaService.update(ecosistema);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") int id) {
        try {
            this.ecosistemaService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
