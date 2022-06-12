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

import com.zoologicoRamon.zoologico.models.RelTipo;
import com.zoologicoRamon.zoologico.services.RelTipoService;

@RestController
@RequestMapping("/rel-tipo")
public class RelTipoController {
    private final RelTipoService relTipoService;

    public RelTipoController(RelTipoService relTipoService) {
        this.relTipoService = relTipoService;
    }

    @GetMapping
    public ResponseEntity<List<RelTipo>> getAll() {
        List<RelTipo> relTipos = null;
        try {
            relTipos = this.relTipoService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relTipos, HttpStatus.OK);
    }

    @GetMapping("/{id_tipo}/{id_animal}")
    public ResponseEntity<RelTipo> getAll(@PathVariable("id_tipo") int id1, @PathVariable("id_animal") int id2) {
        RelTipo relTipo = null;
        try {
            relTipo = this.relTipoService.getById(id1, id2);
            if(relTipo.getId_animal() == 0 || relTipo.getId_tipo() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(relTipo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody RelTipo relTipo) {
        try {
            this.relTipoService.save(relTipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody RelTipo relTipo) {
        try {
            this.relTipoService.update(relTipo);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_tipo}/{id_animal}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id_tipo") int id1, @PathVariable("id_animal") int id2) {
        try {
            this.relTipoService.delete(id1, id2);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
