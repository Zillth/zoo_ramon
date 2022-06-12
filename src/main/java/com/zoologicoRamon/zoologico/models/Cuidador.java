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
public class Cuidador {
    private int id_cuidador;
    private String rfc;
    private String nombre_cuidador;
    private String telefono;


    public Cuidador(String rfc, String nombre_cuidador, String telefono) {
        this.rfc = rfc;
        this.nombre_cuidador = nombre_cuidador;
        this.telefono = telefono;
    }

}
