package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Tipo;
import com.zoologicoRamon.zoologico.repositories.TipoRepo;

@Service
public class TipoService implements IService<Tipo>{
    private final TipoRepo tipoRepo;

    public TipoService(TipoRepo tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    @Override
    public List<Tipo> getAll() throws SQLException {
        return this.tipoRepo.getAll();
    }

    @Override
    public Tipo getById(int id) throws SQLException {
        return this.tipoRepo.getById(id);
    }

    @Override
    public void save(Tipo object) throws SQLException {
        this.tipoRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.tipoRepo.delete(id);
    }

    @Override
    public void update(Tipo object) throws SQLException {
        this.tipoRepo.update(object);
    }
}
