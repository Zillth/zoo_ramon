package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Especie;
import com.zoologicoRamon.zoologico.repositories.EspecieRepo;

@Service
public class EspecieService implements IService<Especie>{
    private final EspecieRepo especieRepo;

    public EspecieService(EspecieRepo especieRepo) {
        this.especieRepo = especieRepo;
    }

    @Override
    public List<Especie> getAll() throws SQLException {
        return this.especieRepo.getAll();
    }

    @Override
    public Especie getById(int id) throws SQLException {
        return this.especieRepo.getById(id);
    }

    @Override
    public void save(Especie object) throws SQLException {
        this.especieRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.especieRepo.delete(id);
    }

    @Override
    public void update(Especie object) throws SQLException {
        this.especieRepo.update(object);
    }
}
