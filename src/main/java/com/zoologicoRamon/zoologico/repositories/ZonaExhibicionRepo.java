package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.ZonaExhibicion;

@Repository
public class ZonaExhibicionRepo implements ICrud<ZonaExhibicion>{

    @Override
    public ZonaExhibicion getByResultSet(ResultSet s) throws SQLException {
        return new ZonaExhibicion(
                s.getInt("id_zona"),
                s.getString("nombre_zona"),
                s.getString("horario_zona"));
    }

    @Override
    public List<ZonaExhibicion> getAll() throws SQLException {
        String sql = String.format("select * from zona_exhibicion");
        ResultSet s = connection.executeQuery(sql);
        List<ZonaExhibicion> zonas = new ArrayList<ZonaExhibicion>();
        while (s.next()) {
            zonas.add(getByResultSet(s));
        }
        connection.disconnect();
        return zonas;
    }

    @Override
    public ZonaExhibicion getById(int id) throws SQLException {
        String sql = String.format("select * from zona_exhibicion where id_zona = %d", id);
        ResultSet s = connection.executeQuery(sql);
        ZonaExhibicion zona_exhibicion;
        if (s.next()) {
            zona_exhibicion = getByResultSet(s);
            connection.disconnect();
            return zona_exhibicion;
        }
        connection.disconnect();
        throw new SQLException("ZonaExhibicion not found");
    }

    @Override
    public void save(ZonaExhibicion object) throws SQLException {
        String sql = String.format(
                "insert into zona_exhibicion values (%1$d, '%2$s', '%3$s')",
                object.getId_zona(),
                object.getNombre_zona(),
                object.getHorario_zona());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("ZonaExhibicion insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from zona_exhibicion where id_zona = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("ZonaExhibicion delete error");
        }
    }

    @Override
    public void update(ZonaExhibicion object) throws SQLException {
        String sql = String.format("update zona_exhibicion set " +
                "nombre_zona = '%1$s'," +
                "horario_zona = '%2$s' " +
                "where id_zona = %3$d",
                object.getNombre_zona(),
                object.getHorario_zona(),
                object.getId_zona());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("ZonaExhibicion update error");
        }
    }
    
}
