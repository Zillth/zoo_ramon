package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Tipo;

@Repository
public class TipoRepo implements ICrud<Tipo> {

    @Override
    public Tipo getByResultSet(ResultSet s) throws SQLException {
        return new Tipo(
                s.getInt("id_tipo"),
                s.getString("nombre_tipo"),
                s.getString("descripcion_tipo"));
    }

    @Override
    public List<Tipo> getAll() throws SQLException {
        String sql = String.format("select * from tipo");
        ResultSet s = connection.executeQuery(sql);
        List<Tipo> tipos = new ArrayList<Tipo>();
        while (s.next()) {
            tipos.add(getByResultSet(s));
        }
        connection.disconnect();
        return tipos;
    }

    @Override
    public Tipo getById(int id) throws SQLException {
        String sql = String.format("select * from tipo where id_tipo = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Tipo tipo;
        if (s.next()) {
            tipo = getByResultSet(s);
            connection.disconnect();
            return tipo;
        }
        connection.disconnect();
        throw new SQLException("Tipo not found");
    }

    @Override
    public void save(Tipo object) throws SQLException {
        String sql = String.format(
                "insert into tipo values (%1$d, '%2$s', '%3$s')",
                object.getId_tipo(),
                object.getNombre_tipo(),
                object.getDescripcion_tipo());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Tipo insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from tipo where id_tipo = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Tipo delete error");
        }
    }

    @Override
    public void update(Tipo object) throws SQLException {
        String sql = String.format("update tipo set " +
                "nombre_tipo = '%1$s'," +
                "descripcion_tipo = '%2$s' " +
                "where id_tipo = %3$d",
                object.getNombre_tipo(),
                object.getDescripcion_tipo(),
                object.getId_tipo());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Tipo update error");
        }
    }

}
