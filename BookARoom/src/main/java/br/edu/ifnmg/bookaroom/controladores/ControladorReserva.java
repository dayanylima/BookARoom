/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.controladores;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.predio.Predio;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import br.edu.ifnmg.bookaroom.sala.Sala;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jefeson
 */
public class ControladorReserva {

    private List<Reserva> reservas;
    private Campus campus;


    public ControladorReserva(Campus campus) {
        this.reservas = new ArrayList<>();
        this.campus = campus;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    private void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(dataAlocacao,
                horaInicio, horaFim,
                equipamentos, sala,
                autorReserva,
                campus = this.campus
        );

        this.addReserva(reserva);
        return reserva;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(dataAlocacao,
                horaInicio, horaFim,
                sala,
                autorReserva,
                campus = this.campus
        );

        this.addReserva(reserva);
        return reserva;
    }

    public List<Reserva> consultarReservasPeriodo(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        List<Reserva> reservasIndisponiveis = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (dataAlocacao.compareTo(reserva.getDataAlocacao()) == 0) {
                long inicio = horaInicio.toNanoOfDay();
                long fim = horaFim.toNanoOfDay();
                long reservaIncio = reserva.getHoraInicio().toNanoOfDay();
                long reservaFim = reserva.getHoraFim().toNanoOfDay();
                if ((inicio <= reservaIncio && (fim >= reservaIncio && fim <= reservaFim))
                        || (inicio >= reservaIncio && inicio <= reservaFim)
                        || ((inicio <= reservaIncio && (fim >= reservaFim)))) {
                    reservasIndisponiveis.add(reserva);
                }
            }
        }
        return reservasIndisponiveis;
    }

    public List<Sala> consultarSalaDisponivel(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {

        List<Predio> predios = campus.getPredios();
        List<Sala> salas = new ArrayList<>();
        List<Sala> salasDisponiveis = new ArrayList<>();

        for (Predio pred : predios) {
            //System.out.println(pred.getSalas()); 
            for (Sala sala : pred.getSalas()) {
                salas.add(sala);
            }
        }

        List<Reserva> reservas = this.consultarReservasPeriodo(dataAlocacao, horaInicio, horaFim);
        List<Sala> salasReservadas = new ArrayList<>();

        for (Reserva reserva : reservas) {
            salasReservadas.add(reserva.getSala());
        }

        boolean encontrou = false;
        for (Sala s : salas) {
            encontrou = false;
            for (Sala sr : salasReservadas) {
                if (s.getNumero() == sr.getNumero()) {
                    encontrou = true;
                }
            }
            if (encontrou == false) {
                salasDisponiveis.add(s);
            }
        }

        return salasDisponiveis;

    }

    public List<Equipamento> consultarEquipamentoDisponivel(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {

        List<Equipamento> equipamentos = new ArrayList<>();
        List<Equipamento> equipamentosDisponiveis = new ArrayList<>();

        equipamentos = campus.getEquipamentos();

        List<Reserva> reservas = this.consultarReservasPeriodo(dataAlocacao, horaInicio, horaFim);
        List<Equipamento> equipamentosReservados = new ArrayList<>();

        for (Reserva reserva : reservas) {

            for (Equipamento e : reserva.getEquipamentos()) {
                equipamentosReservados.add(e);
            }
        }

        boolean encontrou = false;
        for (Equipamento e : equipamentos) {
            encontrou = false;
            for (Equipamento eq : equipamentosReservados) {
                if (e.getCodigo() == eq.getCodigo()) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                equipamentosDisponiveis.add(e);
            }
        }

        return equipamentosDisponiveis;

    }

}
