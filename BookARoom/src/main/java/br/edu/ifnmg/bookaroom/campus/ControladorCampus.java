/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

import java.util.List;

/**
 *
 * @author dayan
 */
public class ControladorCampus {

    public static Campus pesquisarCampus(String campusNome, List<Campus> listaCampus) {

        for (Campus c : listaCampus) {
            if (c.getNome().equalsIgnoreCase(campusNome)) {
                return c;
            }
        }

        return null;
    }

    public static Campus listarCampus(List<Campus> listaCampus) {

        for (Campus c : listaCampus) {
            System.out.println(c.getNome());
        }

        return null;
    }
}
