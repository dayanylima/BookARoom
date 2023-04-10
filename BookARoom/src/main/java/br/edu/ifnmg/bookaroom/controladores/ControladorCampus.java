/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import br.edu.ifnmg.bookaroom.campus.Campus;

import java.util.List;

/**
 *
 * @author dayan
 */
public class ControladorCampus {

    public Campus pesquisarCampus(String nomeCampus) {
        Campus campus = new Campus();
        return campus.pesquisarCampus(nomeCampus);
    }

    public List<Campus> listarCampus() {
        Campus campus = new Campus();
        return campus.listarCampus();
    }
}
