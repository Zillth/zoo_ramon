package com.zoologicoRamon.zoologico.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zoologicoRamon.zoologico.interfaces.ICrud;
import com.zoologicoRamon.zoologico.models.Animal;

@Repository
public class AnimalRepo implements ICrud<Animal> {
    @Override
    public List<Animal> getAll() throws SQLException {
        String sql = String.format("select * from animal a " +
                "inner join especie e on a.id_especie = e.id_especie " +
                "inner join ecosistema ec on a.id_ecosistema = ec.id_ecosistema " +
                "inner join zona_exhibicion z on a.id_zona = z.id_zona");
        ResultSet s = connection.executeQuery(sql);
        List<Animal> animales = new ArrayList<Animal>();
        while (s.next()) {
            animales.add(new Animal(
                    s.getInt("id_animal"),
                    s.getString("nombre_animal"),
                    s.getString("e_conservacion"),
                    s.getString("dieta"),
                    s.getString("reproduccion"),
                    s.getString("adaptacion"),
                    s.getString("amenazas"),
                    s.getInt("id_especie"),
                    s.getString("nombre_especie"),
                    s.getInt("id_zona"),
                    s.getString("nombre_zona"),
                    s.getInt("id_ecosistema"),
                    s.getString("nombre_ecosistema")));
        }
        connection.disconnect();
        return animales;
    }

    @Override
    public Animal getById(int id) throws SQLException {
        String sql = String.format("select * from animal a " +
                "inner join especie e on a.id_especie = e.id_especie " +
                "inner join ecosistema ec on a.id_ecosistema = ec.id_ecosistema " +
                "inner join zona_exhibicion z on a.id_zona = z.id_zona where id_animal = %d", id);
        ResultSet s = connection.executeQuery(sql);
        Animal Animal;
        if (s.next()) {
            Animal = new Animal(
                    s.getInt("id_animal"),
                    s.getString("nombre_animal"),
                    s.getString("e_conservacion"),
                    s.getString("dieta"),
                    s.getString("reproduccion"),
                    s.getString("adaptacion"),
                    s.getString("amenazas"),
                    s.getInt("id_especie"),
                    s.getString("nombre_especie"),
                    s.getInt("id_zona"),
                    s.getString("nombre_zona"),
                    s.getInt("id_ecosistema"),
                    s.getString("nombre_ecosistema"));
            connection.disconnect();
            return Animal;
        }
        connection.disconnect();
        throw new SQLException("Animal not found");
    }

    @Override
    public void save(Animal object) throws SQLException {
        String sql = String.format(
                "insert into animal values (%1$d, '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', %8$d, %9$d, %10$d)",
                object.getId_animal(),
                object.getNombre_animal(),
                object.getE_conservacion(),
                object.getDieta(),
                object.getReproduccion(),
                object.getAdaptacion(),
                object.getAmenazas(),
                object.getId_especie(),
                object.getId_zona(),
                object.getId_ecosistema());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Animal insertion error");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = String.format("delete from animal where id_animal = %d", id);
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Animal delete error");
        }
    }

    @Override
    public void update(Animal object) throws SQLException {
        System.out.println(object.toString());
        String sql = String.format("update animal set " +
                "nombre_animal = '%1$s'," +
                "e_conservacion = '%2$s'," +
                "dieta = '%3$s'," +
                "reproduccion = '%4$s'," +
                "adaptacion = '%5$s'," +
                "amenazas = '%6$s'," +
                "id_especie = %7$d," +
                "id_zona = %8$d," +
                "id_ecosistema = %9$d " +
                "where id_animal = %10$d",
                object.getNombre_animal(),
                object.getE_conservacion(),
                object.getDieta(),
                object.getReproduccion(),
                object.getAdaptacion(),
                object.getAmenazas(),
                object.getId_especie(),
                object.getId_zona(),
                object.getId_ecosistema(),
                object.getId_animal());
        int s = connection.executeUpdate(sql);
        if (s == 0) {
            connection.disconnect();
            throw new SQLException("Animal update error");
        }
    }

    @Override
    public Animal getByResultSet(ResultSet s) throws SQLException {
        return new Animal(
                s.getInt("id_animal"),
                s.getString("nombre_animal"),
                s.getString("e_conservacion"),
                s.getString("dieta"),
                s.getString("reproduccion"),
                s.getString("adaptacion"),
                s.getString("amenazas"),
                s.getInt("id_especie"),
                s.getString("nombre_especie"),
                s.getInt("id_zona"),
                s.getString("nombre_zona"),
                s.getInt("id_ecosistema"),
                s.getString("nombre_ecosistema"));
    }

}
