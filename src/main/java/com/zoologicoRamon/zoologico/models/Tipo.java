package com.zoologicoRamon.zoologico.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tipo {
    private int id_tipo;
    private String nombre_tipo;
    private String descripcion_tipo;

    Tipo(String nombre, String desc) {
        this.nombre_tipo = nombre;
        this.descripcion_tipo = desc;
    }
}
