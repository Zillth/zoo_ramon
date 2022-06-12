package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IServiceRel;
import com.zoologicoRamon.zoologico.models.RelTipo;
import com.zoologicoRamon.zoologico.repositories.RelTipoRepo;

@Service
public class RelTipoService implements IServiceRel<RelTipo>{
    private final RelTipoRepo relTipoRepo;

    public RelTipoService(RelTipoRepo relTipoRepo) {
        this.relTipoRepo = relTipoRepo;
    }

    @Override
    public List<RelTipo> getAll() throws SQLException {
        return this.relTipoRepo.getAll();
    }

    @Override
    public RelTipo getById(int id1, int id2) throws SQLException {
        return this.relTipoRepo.getById(id1, id2);
    }

    @Override
    public void save(RelTipo object) throws SQLException {
        this.relTipoRepo.save(object);
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        this.relTipoRepo.delete(id1, id2);
    }

    @Override
    public void update(RelTipo object) throws SQLException {
        this.relTipoRepo.update(object);
    }
}
