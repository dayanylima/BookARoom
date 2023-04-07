/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.sala;

import br.edu.ifnmg.bookaroom.sala.Sala;
import java.util.List;

/**
 *
 * @author dayan
 */
public class ControladorSala {

    public static Sala pesquisarSala(Integer numero, List<Sala> salas) {

        for (Sala s : salas) {
            if (s.getNumero() == numero) {
                return s;
            }
        }

        return null;
    }

    public static void exibirSalas(List<Sala> salas) {

        for (Sala s : salas) {
            System.out.println(s.toString());
        }

    }

}
