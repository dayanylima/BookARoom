/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author dayan
 */
public class ControladorEquipamento {

    public static Equipamento pesquisarEquipamento(String codigo, List<Equipamento> equipamentos) {

        for (Equipamento e : equipamentos) {
            if (Objects.equals(e.getCodigo(), codigo)) {
                return e;
            }
        }

        return null;
    }

    public static void listarEquipamentos(List<Equipamento> equipamentos) {

        for (Equipamento e : equipamentos) {

            System.out.println(e.toString());
        }

    }
}
