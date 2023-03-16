/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.reservas;

import br.edu.ifnmg.bookaroom.campus.Equipamento;
import br.edu.ifnmg.bookaroom.campus.Funcionario;
import br.edu.ifnmg.bookaroom.campus.Sala;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jefeson
 */
public class ControladorReservas {

    private List<Reserva> reservas;

    public ControladorReservas() {
        this.reservas = new ArrayList<>();
    }

    private void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(dataAlocacao,
                horaInicio, horaFim,
                equipamentos, sala,
                autorReserva
        );

        this.addReserva(reserva);

        return reserva;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva) {
        Reserva reserva = new Reserva(dataAlocacao,
                horaInicio, horaFim,
                sala,
                autorReserva
        );

        this.addReserva(reserva);
        return reserva;
    }

    public List<Reserva> consultarSalaIndisponivel(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        List<Reserva> reservasIndisponiveis = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getDataAlocacao() == dataAlocacao) {
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

    public List<Reserva> consultarSalaDisponivel(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        List<Reserva> reservasDisponiveis = new ArrayList<>();
        Boolean aux = false;
        for (Reserva reserva : reservas) {
            if (reserva.getDataAlocacao() == dataAlocacao) {
                if (reserva.getHoraInicio() == horaInicio && reserva.getHoraFim() == horaFim) {
                    aux = true;
                }
                if (aux == false) {
                    reservasDisponiveis.add(reserva);
                } else {
                    aux = false;
                }

            }

        }
        return reservasDisponiveis;
    }

}
