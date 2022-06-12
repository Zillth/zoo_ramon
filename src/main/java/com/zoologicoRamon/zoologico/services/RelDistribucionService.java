package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IServiceRel;
import com.zoologicoRamon.zoologico.models.RelDistribucion;
import com.zoologicoRamon.zoologico.repositories.RelDistribucionRepo;

@Service
public class RelDistribucionService implements IServiceRel<RelDistribucion>{
    private final RelDistribucionRepo relDistribucionRepo;

    public RelDistribucionService(RelDistribucionRepo relDistribucionRepo) {
        this.relDistribucionRepo = relDistribucionRepo;
    }

    @Override
    public List<RelDistribucion> getAll() throws SQLException {
        return this.relDistribucionRepo.getAll();
    }

    @Override
    public RelDistribucion getById(int id1, int id2) throws SQLException {
        return this.relDistribucionRepo.getById(id1, id2);
    }

    @Override
    public void save(RelDistribucion object) throws SQLException {
        this.relDistribucionRepo.save(object);
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        this.relDistribucionRepo.delete(id1, id2);
    }

    @Override
    public void update(RelDistribucion object) throws SQLException {
        this.relDistribucionRepo.update(object);
    }
}
