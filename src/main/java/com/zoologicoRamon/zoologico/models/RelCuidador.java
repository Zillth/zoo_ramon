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
public class RelCuidador{
    private int id_cuidador;
    private String nombre_cuidador;
    private int id_animal;
    private String nombre_animal;
    private String horario_cuida;


    public RelCuidador(int id_cuidador, int id_animal, String horario_cuida) {
        this.id_cuidador = id_cuidador;
        this.id_animal = id_animal;
        this.horario_cuida = horario_cuida;
    }

}
