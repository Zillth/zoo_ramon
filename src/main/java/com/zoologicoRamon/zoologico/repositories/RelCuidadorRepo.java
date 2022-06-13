package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrudRel;
import com.zoologicoRamon.zoologico.models.RelCuidador;

@Repository
public class RelCuidadorRepo implements ICrudRel<RelCuidador> {

    @Override
    public RelCuidador getByResultSet(ResultSet s) throws SQLException {
        return new RelCuidador(
                s.getInt("id_cuidador"),
                s.getString("nombre_cuidador"),
                s.getInt("id_animal"),
                s.getString("nombre_animal"),
                s.getString("horario_cuida"));
    }

    @Override
    public List<RelCuidador> getAll() throws SQLException {
        String sql = String.format("select * from rel_cuidador r " +
                "inner join animal a on a.id_animal = r.id_animal " +
                "inner join cuidador c on c.id_cuidador = r.id_cuidador");
        ResultSet s = connection.executeQuery(sql);
        List<RelCuidador> relCuidadores = new ArrayList<RelCuidador>();
        while (s.next()) {
            relCuidadores.add(new RelCuidador(
                    s.getInt("id_cuidador"),
                    s.getString("nombre_cuidador"),
                    s.getInt("id_animal"),
                    s.getString("nombre_animal"),
                    s.getString("horario_cuida")));
        }
        connection.disconnect();
        return relCuidadores;
    }

    @Override
    public RelCuidador getById(int id1, int id2) throws SQLException {
        String sql = String.format("select * from rel_cuidador r " +
                "inner join animal a on a.id_animal = r.id_animal " +
                "inner join cuidador c on c.id_cuidador = r.id_cuidador where id_cuidador = %1$d and id_animal = %2$d",
                id1,
                id2);
        ResultSet s = connection.executeQuery(sql);
        RelCuidador rel_cuidador;
        if (s.next()) {
            rel_cuidador = new RelCuidador(
                    s.getInt("id_cuidador"),
                    s.getString("nombre_cuidador"),
                    s.getInt("id_animal"),
                    s.getString("nombre_animal"),
                    s.getString("horario_cuida"));
            connection.disconnect();
            return rel_cuidador;
        }
        connection.disconnect();
        throw new SQLException("RelCuidador not found");
    }

    @Override
    public void save(RelCuidador object) throws SQLException {
        String sql = String.format(
                "insert into rel_cuidador values (%1$d, %2$d, '%3$s')",
                object.getId_cuidador(),
                object.getId_animal(),
                object.getHorario_cuida());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelCuidador insertion error");
        }
    }

    @Override
    public void delete(int id1, int id2) throws SQLException {
        String sql = String.format("delete from rel_cuidador where id_cuidador = %1$d and id_animal = %2$d", id1, id2);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelCuidador delete error");
        }
    }

    @Override
    public void update(RelCuidador object) throws SQLException {
        String sql = String.format("update rel_cuidador set " +
                "id_cuidador = %1$d," +
                "id_animal = %2$d," +
                "horario_cuida = '%3$s' " +
                "where id_cuidador = %1$d and id_animal = %2$d",
                object.getId_cuidador(),
                object.getId_animal(),
                object.getHorario_cuida());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("RelCuidador update error");
        }
    }

}
