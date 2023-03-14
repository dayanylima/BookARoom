/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import br.edu.ifnmg.bookaroom.campus.Funcionario;
import br.edu.ifnmg.bookaroom.campus.Predio;
import br.edu.ifnmg.bookaroom.campus.Sala;
import br.edu.ifnmg.bookaroom.reservas.ControladorReservas;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dayan
 */
public class BookARoom {

    public static void main(String[] args) {

        Sala sala = new Sala(1, 20);
        List<Sala> salas = new ArrayList<Sala>();
        salas.add(sala);

        Predio predio = new Predio("predio 1", salas);
        //System.out.println(predio.getSalasDisponiveis());

        ControladorReservas reservas = new ControladorReservas();

        Date data = new Date();
        Funcionario funcionario = new Funcionario("Jefeson", "Professor", "123");
        LocalTime horaInicio = LocalTime.parse("15:00:00");
        LocalTime horaFim = LocalTime.parse("17:00:00");
        reservas.fazerNovaReserva(data, horaInicio, horaFim, sala, funcionario);
        
        //reservas.fazerNovaReserva(data, LocalTime.parse("05:18:23"), LocalTime.parse("06:18:23"), sala, funcionario);

        
        //List<Reserva> r = reservas.getReservas();
        //System.out.println(reservas.getReservas());
        LocalTime horaInicio2 = LocalTime.parse("16:00:01");
        LocalTime horaFim2 = LocalTime.parse("16:30:00");
//
        List<Reserva> r = reservas.consultarSalaIndisponivel(data, horaInicio2, horaFim2);
        System.out.println(r);

        //List<Reserva> ri = reservas.consultarSalaDisponivel(data, horaInicio, horaFim);
        //System.out.println(ri);
        
//         LocalTime horaInicio2 = LocalTime.parse("23:59:59");
//         System.out.println(horaInicio2.toSecondOfDay());
    }
}