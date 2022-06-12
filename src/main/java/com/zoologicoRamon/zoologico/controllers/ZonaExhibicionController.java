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

import com.zoologicoRamon.zoologico.models.ZonaExhibicion;
import com.zoologicoRamon.zoologico.services.ZonaExhibicionService;

@RestController
@RequestMapping("/zona")
public class ZonaExhibicionController {
    private final ZonaExhibicionService zonaExhibicionService;

    public ZonaExhibicionController(ZonaExhibicionService zonaExhibicionService) {
        this.zonaExhibicionService = zonaExhibicionService;
    }

    @GetMapping
    public ResponseEntity<List<ZonaExhibicion>> getAll() {
        List<ZonaExhibicion> zonasExhibicion = null;
        try {
            zonasExhibicion = this.zonaExhibicionService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(zonasExhibicion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaExhibicion> getAll(@PathVariable("id") int id) {
        ZonaExhibicion zonaExhibicion = null;
        try {
            zonaExhibicion = this.zonaExhibicionService.getById(id);
            if(zonaExhibicion.getId_zona() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(zonaExhibicion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody ZonaExhibicion zonaExhibicion) {
        try {
            this.zonaExhibicionService.save(zonaExhibicion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody ZonaExhibicion zonaExhibicion) {
        try {
            this.zonaExhibicionService.update(zonaExhibicion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") int id) {
        try {
            this.zonaExhibicionService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
