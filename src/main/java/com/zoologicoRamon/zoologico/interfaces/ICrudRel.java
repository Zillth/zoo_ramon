package com.zoologicoRamon.zoologico.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zoologicoRamon.zoologico.database.MySQLConnection;

public interface ICrudRel <T>{
    final MySQLConnection connection = new MySQLConnection();

    T getByResultSet(ResultSet s) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id1, int id2) throws SQLException;

    void save(T object) throws SQLException;

    void delete(int id1, int id2) throws SQLException;

    void update(T object) throws SQLException;
}
