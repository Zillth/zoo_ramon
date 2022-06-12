package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Ecosistema;
import com.zoologicoRamon.zoologico.repositories.EcosistemaRepo;

@Service
public class EcosistemaService implements IService<Ecosistema>{
    private final EcosistemaRepo ecosistemaRepo;

    public EcosistemaService(EcosistemaRepo ecosistemaRepo) {
        this.ecosistemaRepo = ecosistemaRepo;
    }

    @Override
    public List<Ecosistema> getAll() throws SQLException {
        return this.ecosistemaRepo.getAll();
    }

    @Override
    public Ecosistema getById(int id) throws SQLException {
        return this.ecosistemaRepo.getById(id);
    }

    @Override
    public void save(Ecosistema object) throws SQLException {
        this.ecosistemaRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.ecosistemaRepo.delete(id);
    }

    @Override
    public void update(Ecosistema object) throws SQLException {
        this.ecosistemaRepo.update(object);
    }
}
