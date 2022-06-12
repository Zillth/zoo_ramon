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

import com.zoologicoRamon.zoologico.models.RelCuidador;
import com.zoologicoRamon.zoologico.services.RelCuidadorService;

@RestController
@RequestMapping("/rel-cuidador")
public class RelCuidadorController {
    private final RelCuidadorService relCuidadorService;

    public RelCuidadorController(RelCuidadorService relCuidadorService) {
        this.relCuidadorService = relCuidadorService;
    }

    @GetMapping
    public ResponseEntity<List<RelCuidador>> getAll() {
        List<RelCuidador> relCuidadores = null;
        try {
            relCuidadores = this.relCuidadorService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relCuidadores, HttpStatus.OK);
    }

    @GetMapping("/{id_cuidador}/{id_animal}")
    public ResponseEntity<RelCuidador> getAll(@PathVariable("id_cuidador") int id1, @PathVariable("id_animal") int id2) {
        RelCuidador relCuidador = null;
        try {
            relCuidador = this.relCuidadorService.getById(id1, id2);
            if(relCuidador.getId_animal() == 0 || relCuidador.getId_cuidador() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relCuidador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody RelCuidador relCuidador) {
        try {
            this.relCuidadorService.save(relCuidador);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody RelCuidador relCuidador) {
        try {
            this.relCuidadorService.update(relCuidador);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_cuidador}/{id_animal}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id_cuidador") int id1, @PathVariable("id_animal") int id2) {
        try {
            this.relCuidadorService.delete(id1, id2);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
