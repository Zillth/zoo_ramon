package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Distribucion;

@Repository
public class DistribucionRepo implements ICrud<Distribucion> {

    @Override
    public Distribucion getByResultSet(ResultSet s) throws SQLException {
        return new Distribucion(
                s.getInt("id_distribucion"),
                s.getString("nombre_distribucion"),
                s.getString("descripcion_distribucion"));
    }

    @Override
    public List<Distribucion> getAll() throws SQLException {
        String sql = String.format("select * from distribucion");
        ResultSet s = connection.executeQuery(sql);
        List<Distribucion> cuidadores = new ArrayList<Distribucion>();
        while (s.next()) {
            cuidadores.add(getByResultSet(s));
        }
        connection.disconnect();
        return cuidadores;
    }

    @Override
    public Distribucion getById(int id) throws SQLException {
        String sql = String.format("select * from distribucion where id_distribucion = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Distribucion distribucion;
        if (s.next()) {
            distribucion = getByResultSet(s);
            connection.disconnect();
            return distribucion;
        }
        connection.disconnect();
        throw new SQLException("Distribucion not found");
    }

    @Override
    public void save(Distribucion object) throws SQLException {
        String sql = String.format(
                "insert into distribucion values (%1$d, '%2$s', '%3$s')",
                object.getId_distribucion(),
                object.getNombre_distribucion(),
                object.getDescripcion_distribucion());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Distribucion insertion error");
        }

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from distribucion where id_distribucion = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Distribucion delete error");
        }

    }

    @Override
    public void update(Distribucion object) throws SQLException {
        String sql = String.format("update distribucion set " +
                "nombre_distribucion = '%1$s'," +
                "descripcion_distribucion = '%2$s' " +
                "where id_distribucion = %3$d",
                object.getNombre_distribucion(),
                object.getDescripcion_distribucion(),
                object.getId_distribucion());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Distribucion update error");
        }
    }

}
