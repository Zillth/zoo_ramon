package com.zoologicoRamon.zoologico.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zoologicoRamon.zoologico.interfaces.IService;
import com.zoologicoRamon.zoologico.models.Animal;
import com.zoologicoRamon.zoologico.repositories.AnimalRepo;

@Service
public class AnimalService implements IService<Animal>{

    private final AnimalRepo animalRepo;

    public AnimalService(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public List<Animal> getAll() throws SQLException {
        return this.animalRepo.getAll();
    }

    @Override
    public Animal getById(int id) throws SQLException {
        return this.animalRepo.getById(id);
    }

    @Override
    public void save(Animal object) throws SQLException {
        this.animalRepo.save(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        this.animalRepo.delete(id);
    }

    @Override
    public void update(Animal object) throws SQLException {
        this.animalRepo.update(object);
    }

    
}
