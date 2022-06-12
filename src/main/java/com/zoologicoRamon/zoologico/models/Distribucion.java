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
public class Distribucion {
    private int id_distribucion;
    private String nombre_distribucion;
    private String descripcion_distribucion;


    public Distribucion(String nombre_distribucion, String descripcion_distribucion) {
        this.nombre_distribucion = nombre_distribucion;
        this.descripcion_distribucion = descripcion_distribucion;
    }

}
