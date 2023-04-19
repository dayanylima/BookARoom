/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.reservas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifnmg.bookaroom.bancodedados.BancoDeDados;
import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.predio.Predio;
import br.edu.ifnmg.bookaroom.sala.Sala;

/**
 *
 * @author Jefeson
 */
public class Reserva {

    private Date dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private List<Equipamento> equipamentos;
    private Sala sala;
    private Funcionario autorReserva;
    private Campus campus;


    public Reserva(Campus campus) {
        equipamentos = new ArrayList<>();
        this.campus = campus;
    }

    public Date getDataAlocacao() {
        return dataAlocacao;
    }

    public String getDataAlocacaoString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(this.dataAlocacao);

        return dataFormatada;
    }

    public void setDataAlocacao(Date dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getAutorReserva() {
        return autorReserva;
    }

    public void setAutorReserva(Funcionario autorReserva) {
        this.autorReserva = autorReserva;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva) {
        BancoDeDados bd = BancoDeDados.getInstance();
        this.setDataAlocacao(dataAlocacao);
        this.setHoraInicio(horaInicio);
        this.setHoraFim(horaFim);
        this.setSala(sala);
        this.setAutorReserva(autorReserva);
        bd.addReserva(this);
        return this;
    }

    public Reserva fazerNovaReserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva) {
        BancoDeDados bd = BancoDeDados.getInstance();
        this.setDataAlocacao(dataAlocacao);
        this.setHoraInicio(horaInicio);
        this.setHoraFim(horaFim);
        this.setSala(sala);
        this.setAutorReserva(autorReserva);
        this.setEquipamentos(equipamentos);
        bd.addReserva(this);
        return this;
    }

    public List<Reserva> consultarReservasPeriodo(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        List<Reserva> reservasIndisponiveis = new ArrayList<>();
        BancoDeDados bd = BancoDeDados.getInstance();
        List<Reserva> reservas = bd.getReserva();
        reservas.removeIf(element -> (element.campus != this.campus)); //testar
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

        for (Predio predio : predios) {
            for (Sala sala : predio.getSalas()) {
                salas.add(sala);
            }
        }

        List<Reserva> reservas = this.consultarReservasPeriodo(dataAlocacao, horaInicio, horaFim);
        List<Sala> salasReservadas = new ArrayList<>();

        for (Reserva reserva : reservas) {
            salasReservadas.add(reserva.getSala());
        }

        boolean encontrou = false;
        for (Sala sala : salas) {
            encontrou = false;
            for (Sala salaReservada : salasReservadas) {
                if (sala.getNumero() == salaReservada.getNumero()) {
                    encontrou = true;
                }
            }
            if (encontrou == false) {
                salasDisponiveis.add(sala);
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

            for (Equipamento equipamento : reserva.getEquipamentos()) {
                equipamentosReservados.add(equipamento);
            }
        }

        boolean encontrou = false;
        for (Equipamento equipamento : equipamentos) {
            encontrou = false;
            for (Equipamento equipamentoReservado : equipamentosReservados) {
                if (equipamento.getCodigo() == equipamentoReservado.getCodigo()) {
                    encontrou = true;
                }
            }
            if (!encontrou) {
                equipamentosDisponiveis.add(equipamento);
            }
        }

        return equipamentosDisponiveis;

    }

    public List<Reserva> listarReservas() {
        BancoDeDados bd = BancoDeDados.getInstance();
        return bd.getReserva();
    }

    public List<Reserva> listarReservasAtivas() throws ParseException {
        BancoDeDados bd = BancoDeDados.getInstance();
        List<Reserva> listaReservas =  bd.getReserva();
        List<Reserva> reservasAtivas = new ArrayList<>();

        Date dataAtual = new Date();
        LocalTime horaAtual = LocalDateTime.ofInstant(dataAtual.toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        for (Reserva r : listaReservas) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            dataAtual = sdf.parse(sdf.format(dataAtual));
            int situacaoDiaReserva = r.getDataAlocacao().compareTo(dataAtual);

            boolean horaReservaTerminaFuturo = r.getHoraFim().compareTo(horaAtual) > 0;
            if (situacaoDiaReserva == 0 && horaReservaTerminaFuturo || situacaoDiaReserva > 0)
                reservasAtivas.add(r);
        }
        return reservasAtivas;
    }

    public List<Reserva> listarReservasInativas() throws ParseException{
        BancoDeDados bd = BancoDeDados.getInstance();
        List<Reserva> listaReservas =  bd.getReserva();
        List<Reserva> reservasInativas = new ArrayList<>();

        Date dataAtual = new Date();
        LocalTime horaAtual = LocalDateTime.ofInstant(dataAtual.toInstant(),
                ZoneId.systemDefault()).toLocalTime();

        for (Reserva r : listaReservas) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            dataAtual = sdf.parse(sdf.format(dataAtual));
            int situacaoDiaReserva = r.getDataAlocacao().compareTo(dataAtual);

            boolean horaReservaTerminaFuturo = r.getHoraFim().compareTo(horaAtual) > 0;
            if (situacaoDiaReserva == 0 && horaReservaTerminaFuturo || situacaoDiaReserva > 0) {
                continue;
            } else {
                reservasInativas.add(r);
            }
        }
        return reservasInativas;
    }


    @Override
    public String toString() {
        return "Reserva{" + "dataAlocacao=" + this.getDataAlocacaoString() + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", equipamentos=" + equipamentos + ", sala=" + sala + ", autorReserva=" + autorReserva + '}';
    }

}
