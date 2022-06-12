package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Cuidador;
import com.zoologicoRamon.zoologico.repositories.CuidadorRepo;

@Service
public class CuidadorService implements IService<Cuidador>{
    private final CuidadorRepo cuidadorRepo;

    public CuidadorService(CuidadorRepo cuidadorRepo) {
        this.cuidadorRepo = cuidadorRepo;
    }

    @Override
    public List<Cuidador> getAll() throws SQLException {
        return this.cuidadorRepo.getAll();
    }

    @Override
    public Cuidador getById(int id) throws SQLException {
        return this.cuidadorRepo.getById(id);
    }

    @Override
    public void save(Cuidador object) throws SQLException {
        this.cuidadorRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.cuidadorRepo.delete(id);
    }

    @Override
    public void update(Cuidador object) throws SQLException {
        this.cuidadorRepo.update(object);
    }
}
