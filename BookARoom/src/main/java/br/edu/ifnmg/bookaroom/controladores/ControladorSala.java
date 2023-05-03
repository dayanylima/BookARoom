/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import java.util.List;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.sala.Sala;

/**
 *
 * @author dayan
 */
public class ControladorSala {

    public Sala pesquisarSala(int numeroSala, Campus campus) {
        Sala sala = new Sala();
        return sala.pesquisarSala(numeroSala, campus);
    }

    public List<Sala> retornarlistaDeSalas(Campus campus) {
        Sala sala = new Sala();
        return sala.listarSalas(campus);
    }
}
