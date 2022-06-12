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
public class Especie {
    private int id_especie;
    private String nombre_especie;

    Especie(String nombre) {
        this.nombre_especie = nombre;
    }
}
