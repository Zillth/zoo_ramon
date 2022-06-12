package com.zoologicoRamon.zoologico.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IServiceRel <T>{
    List<T> getAll() throws SQLException;

    T getById(int id1, int id2) throws SQLException;

    void save(T object) throws SQLException;

    void delete(int id1, int id2) throws SQLException;

    void update(T object) throws SQLException;
}
