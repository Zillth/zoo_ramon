package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Especie;

@Repository
public class EspecieRepo implements ICrud<Especie> {

    @Override
    public Especie getByResultSet(ResultSet s) throws SQLException {
        return new Especie(
                s.getInt("id_especie"),
                s.getString("nombre_especie"));
    }

    @Override
    public List<Especie> getAll() throws SQLException {
        String sql = String.format("select * from especie");
        ResultSet s = connection.executeQuery(sql);
        List<Especie> cuidadores = new ArrayList<Especie>();
        while (s.next()) {
            cuidadores.add(getByResultSet(s));
        }
        connection.disconnect();
        return cuidadores;
    }

    @Override
    public Especie getById(int id) throws SQLException {
        String sql = String.format("select * from especie where id_especie = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Especie especie;
        if (s.next()) {
            especie = getByResultSet(s);
            connection.disconnect();
            return especie;
        }
        connection.disconnect();
        throw new SQLException("Especie not found");
    }

    @Override
    public void save(Especie object) throws SQLException {
        String sql = String.format(
                "insert into especie values (%1$d, '%2$s')",
                object.getId_especie(),
                object.getNombre_especie());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Especie insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from especie where id_especie = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Especie delete error");
        }
    }

    @Override
    public void update(Especie object) throws SQLException {
        String sql = String.format("update especie set " +
                "nombre_especie = '%1$s'" +
                "where id_especie = %2$d",
                object.getNombre_especie(),
                object.getId_especie());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Especie update error");
        }
    }

}
