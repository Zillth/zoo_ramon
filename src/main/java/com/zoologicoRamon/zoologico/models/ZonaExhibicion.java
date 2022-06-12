package com.zoologicoRamon.zoologico.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ZonaExhibicion {
    private int id_zona;
    private String nombre_zona;
    private String horario_zona;

    ZonaExhibicion(String nombre, String horario) {
        this.nombre_zona = nombre;
        this.horario_zona = horario;
    }
}
