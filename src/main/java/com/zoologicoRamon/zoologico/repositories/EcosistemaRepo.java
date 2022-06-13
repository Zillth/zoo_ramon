package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Ecosistema;

@Repository
public class EcosistemaRepo implements ICrud<Ecosistema>{

    @Override
    public Ecosistema getByResultSet(ResultSet s) throws SQLException {
        return new Ecosistema(
                s.getInt("id_ecosistema"),
                s.getString("nombre_ecosistema"));
    }

    @Override
    public List<Ecosistema> getAll() throws SQLException {
        String sql = String.format("select * from ecosistema");
        ResultSet s = connection.executeQuery(sql);
        List<Ecosistema> ecosistemas = new ArrayList<Ecosistema>();
        while (s.next()) {
            ecosistemas.add(new Ecosistema(
                s.getInt("id_ecosistema"),
                s.getString("nombre_ecosistema")));
        }
        connection.disconnect();
        return ecosistemas;
    }

    @Override
    public Ecosistema getById(int id) throws SQLException {
        String sql = String.format("select * from ecosistema where id_ecosistema = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Ecosistema ecosistema;
        if (s.next()) {
            ecosistema = new Ecosistema(
                s.getInt("id_ecosistema"),
                s.getString("nombre_ecosistema"));
            connection.disconnect();
            return ecosistema;
        }
        connection.disconnect();
        throw new SQLException("Ecosistema not found");
    }

    @Override
    public void save(Ecosistema object) throws SQLException {
        String sql = String.format(
                "insert into ecosistema values (%1$d, '%2$s')",
                object.getId_ecosistema(),
                object.getNombre_ecosistema());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Ecosistema insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from ecosistema where id_ecosistema = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Ecosistema delete error");
        }
    }

    @Override
    public void update(Ecosistema object) throws SQLException {
        String sql = String.format("update ecosistema set " +
                "nombre_ecosistema = '%1$s'" +
                "where id_ecosistema = %2$d",
                object.getNombre_ecosistema(),
                object.getId_ecosistema());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Ecosistema update error");
        }
    }
    
}
