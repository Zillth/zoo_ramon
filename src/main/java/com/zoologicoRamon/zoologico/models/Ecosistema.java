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
public class Ecosistema {
    private int id_ecosistema;
    private String nombre_ecosistema;


    public Ecosistema(String nombre_ecosistema) {
        this.nombre_ecosistema = nombre_ecosistema;
    }

}
