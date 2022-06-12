package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IServiceRel;
import com.zoologicoRamon.zoologico.models.RelCuidador;
import com.zoologicoRamon.zoologico.repositories.RelCuidadorRepo;

@Service
public class RelCuidadorService implements IServiceRel<RelCuidador>{
    private final RelCuidadorRepo relCuidadorRepo;

    public RelCuidadorService(RelCuidadorRepo relCuidadorRepo) {
        this.relCuidadorRepo = relCuidadorRepo;
    }

    @Override
    public List<RelCuidador> getAll() throws SQLException {
        return this.relCuidadorRepo.getAll();
    }

    @Override
    public RelCuidador getById(int id1, int id2) throws SQLException {
        return this.relCuidadorRepo.getById(id1, id2);
    }

    @Override
    public void save(RelCuidador object) throws SQLException {
        this.relCuidadorRepo.save(object);
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        this.relCuidadorRepo.delete(id1, id2);
    }

    @Override
    public void update(RelCuidador object) throws SQLException {
        this.relCuidadorRepo.update(object);
    }
}
