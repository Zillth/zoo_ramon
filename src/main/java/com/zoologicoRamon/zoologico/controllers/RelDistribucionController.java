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

import com.zoologicoRamon.zoologico.models.RelDistribucion;
import com.zoologicoRamon.zoologico.services.RelDistribucionService;

@RestController
@RequestMapping("/rel-distribucion")
public class RelDistribucionController {
    private final RelDistribucionService relDistribucionService;

    public RelDistribucionController(RelDistribucionService relDistribucionService) {
        this.relDistribucionService = relDistribucionService;
    }

    @GetMapping
    public ResponseEntity<List<RelDistribucion>> getAll() {
        List<RelDistribucion> relDistribuciones = null;
        try {
            relDistribuciones = this.relDistribucionService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relDistribuciones, HttpStatus.OK);
    }

    @GetMapping("/{id_distribucion}/{id_animal}")
    public ResponseEntity<RelDistribucion> getAll(@PathVariable("id_distribucion") int id1, @PathVariable("id_animal") int id2) {
        RelDistribucion relDistribucion = null;
        try {
            relDistribucion = this.relDistribucionService.getById(id1, id2);
            if(relDistribucion.getId_animal() == 0 || relDistribucion.getId_distribucion() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relDistribucion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody RelDistribucion relDistribucion) {
        try {
            this.relDistribucionService.save(relDistribucion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody RelDistribucion relDistribucion) {
        try {
            this.relDistribucionService.update(relDistribucion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_distribucion}/{id_animal}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id_distribucion") int id1, @PathVariable("id_animal") int id2) {
        try {
            this.relDistribucionService.delete(id1, id2);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
