/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.edu.ifnmg.bookaroom;

import br.edu.ifnmg.bookaroom.campus.Predio;
import br.edu.ifnmg.bookaroom.campus.Sala;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dayan
 */
public class BookARoom {

    public static void main(String[] args) {
        Date data = new Date();
        System.out.println(data);
        Sala sala = new Sala(1,20);
        List<Sala> salas = new ArrayList<Sala>();
        salas.add(sala);

        Predio predio = new Predio("predio 1", salas);
        System.out.println(predio.getSalasDisponiveis());
    }
}
