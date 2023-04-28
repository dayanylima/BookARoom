/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import br.edu.ifnmg.bookaroom.sala.Sala;

/**
 *
 * @author Jefeson
 */
public class ControladorReserva {

    public Reserva fazerReserva(Campus campus, Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(campus);
        reserva.fazerNovaReserva(dataAlocacao, horaInicio, horaFim, sala, autorReserva);
        return reserva;
    }

    public Reserva fazerReservaComEquipamento(Campus campus, Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(campus);
        reserva.fazerNovaReserva(dataAlocacao, horaInicio, horaFim, equipamentos, sala, autorReserva);
        return reserva;
    }

    public List<Reserva> consultarReservasPeriodo(Campus campus, Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        Reserva reserva = new Reserva(campus);
        return reserva.consultarReservasPeriodo(dataAlocacao, horaInicio, horaFim);
    }

    public List<Sala> consultarSalaDisponivel(Campus campus, Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        Reserva reserva = new Reserva(campus);
        return reserva.consultarSalaDisponivel(dataAlocacao, horaInicio, horaFim);
    }

    public List<Equipamento> consultarEquipamentoDisponivel(Campus campus, Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        Reserva reserva = new Reserva(campus);
        return reserva.consultarEquipamentoDisponivel(dataAlocacao, horaInicio, horaFim);
    }

    public List<Reserva> retornarListaDeReservas(Campus campus) {
        Reserva reserva = new Reserva(campus);
        return reserva.listarReservas();
    }

    public List<Reserva> listarReservasAtivas(Campus campus) {
        Reserva reserva = new Reserva(campus);
        List<Reserva> reservasAtivas = new ArrayList<>();
        try {
            reservasAtivas =  reserva.listarReservasAtivas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reservasAtivas;
    }

    public List<Reserva> listarReservasInativas(Campus campus) {
        Reserva reserva = new Reserva(campus);
        List<Reserva> reservasInativas = new ArrayList<>();
        try {
            reservasInativas =  reserva.listarReservasInativas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reservasInativas;
    }
}
