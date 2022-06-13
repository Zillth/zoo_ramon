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
public class Animal {
    private int id_animal;
    private String nombre_animal;
    private String e_conservacion;
    private String dieta;
    private String reproduccion;
    private String adaptacion;
    private String amenazas;
    private int id_especie;
    private String nombre_especie;
    private int id_zona;
    private String nombre_zona;
    private int id_ecosistema;
    private String nombre_ecosistema;


    public Animal(String nombre_animal, String e_conservacion, String dieta, String reproduccion, String adaptacion, String amenazas, int id_especie, int id_zona, int id_ecosistema) {
        this.nombre_animal = nombre_animal;
        this.e_conservacion = e_conservacion;
        this.dieta = dieta;
        this.reproduccion = reproduccion;
        this.adaptacion = adaptacion;
        this.amenazas = amenazas;
        this.id_especie = id_especie;
        this.id_zona = id_zona;
        this.id_ecosistema = id_ecosistema;
    }

}
