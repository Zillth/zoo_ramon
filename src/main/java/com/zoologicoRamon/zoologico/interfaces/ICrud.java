package com.zoologicoRamon.zoologico.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zoologicoRamon.zoologico.database.MySQLConnection;

public interface ICrud <T>{
    final MySQLConnection connection = new MySQLConnection();

    T getByResultSet(ResultSet s) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    void save(T object) throws SQLException;

    void delete(int id) throws SQLException;

    void update(T object) throws SQLException;
}
