/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import java.util.List;

/**
 *
 * @author dayan
 */
public class ControladorFuncionario {

    public static Funcionario pesquisarFuncionario(String funcionarioNome, List<Funcionario> lista) {

        for (Funcionario f : lista) {
            if (f.getNome().equalsIgnoreCase(funcionarioNome)) {
                return f;
            }
        }

        return null;
    }

    public static void listarFuncionario(List<Funcionario> lista) {

        for (Funcionario f : lista) {
                System.out.println(f.toString());
        }

    }
}
