package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrudRel;
import com.zoologicoRamon.zoologico.models.RelTipo;

@Repository
public class RelTipoRepo implements ICrudRel<RelTipo>{

    @Override
    public RelTipo getByResultSet(ResultSet s) throws SQLException {
        return new RelTipo(
            s.getInt("id_tipo"),
            s.getInt("id_animal"));
    }

    @Override
    public List<RelTipo> getAll() throws SQLException {
        String sql = String.format("select * from rel_tipo");
        ResultSet s = connection.executeQuery(sql);
        List<RelTipo> relTipos = new ArrayList<RelTipo>();
        while (s.next()) {
            relTipos.add(getByResultSet(s));
        }
        connection.disconnect();
        return relTipos;
    }

    @Override
    public RelTipo getById(int id1, int id2) throws SQLException {
        String sql = String.format("select * from rel_tipo where id_tipo = %1$d and id_animal = %2$d", id1,
                id2);
        ResultSet s = connection.executeQuery(sql);
        RelTipo rel_tipo;
        if (s.next()) {
            rel_tipo = getByResultSet(s);
            connection.disconnect();
            return rel_tipo;
        }
        connection.disconnect();
        throw new SQLException("RelTipo not found");
    }

    @Override
    public void save(RelTipo object) throws SQLException {
        String sql = String.format(
                "insert into rel_tipo values (%1$d, %2$d)",
                object.getId_tipo(),
                object.getId_animal());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelTipo insertion error");
        }
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        String sql = String.format("delete from rel_tipo where id_tipo = %1$d and id_animal = %2$d", id1, id2);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelTipo delete error");
        }
    }

    @Override
    public void update(RelTipo object) throws SQLException {
        String sql = String.format("update rel_tipo set " +
                "id_tipo = %1$d," +
                "id_animal = %2$d" +
                "where id_tipo = %1$d and id_animal = %2$d",
                object.getId_tipo(),
                object.getId_animal());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelTipo update error");
        }
    }
    
}
