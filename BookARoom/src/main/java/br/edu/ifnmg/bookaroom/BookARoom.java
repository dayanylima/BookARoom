/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import java.text.ParseException;

import br.edu.ifnmg.bookaroom.bancodedados.BancoDeDados;
import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.interfaceUsuario.Console;

/**
 *
 * @author dayan
 */
public class BookARoom {
    public static void main(String[] args) throws ParseException {
        for(Campus campus : BancoDeDados.getInstance().getListaCampus()){
            BancoDeDados.getInstance().definirHorarioAulas(campus);
        }
        Console console = new Console();
        console.start();
        System.exit(0);
    }
}
