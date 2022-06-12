package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrudRel;
import com.zoologicoRamon.zoologico.models.RelDistribucion;

@Repository
public class RelDistribucionRepo implements ICrudRel<RelDistribucion>{

    @Override
    public RelDistribucion getByResultSet(ResultSet s) throws SQLException {
        return new RelDistribucion(
            s.getInt("id_distribucion"),
            s.getInt("id_animal"));
    }

    @Override
    public List<RelDistribucion> getAll() throws SQLException {
        String sql = String.format("select * from rel_distribucion");
        ResultSet s = connection.executeQuery(sql);
        List<RelDistribucion> relDistrobuidores = new ArrayList<RelDistribucion>();
        while (s.next()) {
            relDistrobuidores.add(getByResultSet(s));
        }
        connection.disconnect();
        return relDistrobuidores;
    }

    @Override
    public RelDistribucion getById(int id1, int id2) throws SQLException {
        String sql = String.format("select * from rel_distribucion where id_distribucion = %1$d and id_animal = %2$d", id1,
                id2);
        ResultSet s = connection.executeQuery(sql);
        RelDistribucion rel_distribucion;
        if (s.next()) {
            rel_distribucion = getByResultSet(s);
            connection.disconnect();
            return rel_distribucion;
        }
        connection.disconnect();
        throw new SQLException("RelDistribucion not found");
    }

    @Override
    public void save(RelDistribucion object) throws SQLException {
        String sql = String.format(
                "insert into rel_distribucion values (%1$d, %2$d)",
                object.getId_distribucion(),
                object.getId_animal());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelDistribucion insertion error");
        }
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        String sql = String.format("delete from rel_distribucion where id_distribucion = %1$d and id_animal = %2$d", id1, id2);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelDistribucion delete error");
        }
    }

    @Override
    public void update(RelDistribucion object) throws SQLException {
        String sql = String.format("update rel_distribucion set " +
                "id_distribucion = %1$d," +
                "id_animal = %2$d" +
                "where id_distribucion = %1$d and id_animal = %2$d",
                object.getId_distribucion(),
                object.getId_animal());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelDistribucion update error");
        }
    }
    
}
