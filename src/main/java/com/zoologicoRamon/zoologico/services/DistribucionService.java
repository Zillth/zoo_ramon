package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Distribucion;
import com.zoologicoRamon.zoologico.repositories.DistribucionRepo;

@Service
public class DistribucionService implements IService<Distribucion>{
    private final DistribucionRepo distribucionRepo;

    public DistribucionService(DistribucionRepo distribucionRepo) {
        this.distribucionRepo = distribucionRepo;
    }

    @Override
    public List<Distribucion> getAll() throws SQLException {
        return this.distribucionRepo.getAll();
    }

    @Override
    public Distribucion getById(int id) throws SQLException {
        return this.distribucionRepo.getById(id);
    }

    @Override
    public void save(Distribucion object) throws SQLException {
        this.distribucionRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.distribucionRepo.delete(id);
    }

    @Override
    public void update(Distribucion object) throws SQLException {
        this.distribucionRepo.update(object);
    }
}
