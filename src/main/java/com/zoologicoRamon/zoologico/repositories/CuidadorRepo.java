package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Cuidador;

@Repository
public class CuidadorRepo implements ICrud<Cuidador> {

    @Override
    public Cuidador getByResultSet(ResultSet s) throws SQLException {
        return new Cuidador(
                s.getInt("id_cuidador"),
                s.getString("rfc"),
                s.getString("nombre_cuidador"),
                s.getString("telefono"));
    }

    @Override
    public List<Cuidador> getAll() throws SQLException {
        String sql = String.format("select * from cuidador");
        ResultSet s = connection.executeQuery(sql);
        List<Cuidador> cuidadores = new ArrayList<Cuidador>();
        while (s.next()) {
            cuidadores.add(new Cuidador(
                    s.getInt("id_cuidador"),
                    s.getString("rfc"),
                    s.getString("nombre_cuidador"),
                    s.getString("telefono")));
        }
        connection.disconnect();
        return cuidadores;
    }

    @Override
    public Cuidador getById(int id) throws SQLException {
        String sql = String.format("select * from cuidador where id_cuidador = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Cuidador cuidador;
        if (s.next()) {
            cuidador = new Cuidador(
                    s.getInt("id_cuidador"),
                    s.getString("rfc"),
                    s.getString("nombre_cuidador"),
                    s.getString("telefono"));
            connection.disconnect();
            return cuidador;
        }
        connection.disconnect();
        throw new SQLException("Cuidador not found");
    }

    @Override
    public void save(Cuidador object) throws SQLException {
        String sql = String.format(
                "insert into cuidador values (%1$d, '%2$s', '%3$s', '%4$s')",
                object.getId_cuidador(),
                object.getRfc(),
                object.getNombre_cuidador(),
                object.getTelefono());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Cuidador insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from cuidador where id_cuidador = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Cuidador delete error");
        }
    }

    @Override
    public void update(Cuidador object) throws SQLException {
        String sql = String.format("update cuidador set " +
                "rfc = '%1$s'," +
                "nombre_cuidador = '%2$s'," +
                "telefono = '%3$s' " +
                "where id_cuidador = %4$d",
                object.getRfc(),
                object.getNombre_cuidador(),
                object.getTelefono(),
                object.getId_cuidador());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Cuidador update error");
        }
    }

}
