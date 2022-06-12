package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.ZonaExhibicion;
import com.zoologicoRamon.zoologico.repositories.ZonaExhibicionRepo;

@Service
public class ZonaExhibicionService implements IService<ZonaExhibicion>{
    private final ZonaExhibicionRepo zonaExhibicionRepo;

    public ZonaExhibicionService(ZonaExhibicionRepo zonaExhibicionRepo) {
        this.zonaExhibicionRepo = zonaExhibicionRepo;
    }

    @Override
    public List<ZonaExhibicion> getAll() throws SQLException {
        return this.zonaExhibicionRepo.getAll();
    }

    @Override
    public ZonaExhibicion getById(int id) throws SQLException {
        return this.zonaExhibicionRepo.getById(id);
    }

    @Override
    public void save(ZonaExhibicion object) throws SQLException {
        this.zonaExhibicionRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.zonaExhibicionRepo.delete(id);
    }

    @Override
    public void update(ZonaExhibicion object) throws SQLException {
        this.zonaExhibicionRepo.update(object);
    }
}
