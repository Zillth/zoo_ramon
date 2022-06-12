package com.zoologicoRamon.zoologico.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    void save(T object) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T object) throws SQLException;
}
