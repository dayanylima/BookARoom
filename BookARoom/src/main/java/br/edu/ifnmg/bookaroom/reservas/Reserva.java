/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.reservas;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.sala.Sala;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Reserva() {
        equipamentos = new ArrayList<>();
    }

    public Reserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva, Campus campus) {
        this();
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.equipamentos = equipamentos;
        this.sala = sala;
        this.autorReserva = autorReserva;
        this.campus = campus;
    }

    public Reserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva, Campus campus) {
        this();
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.sala = sala;
        this.autorReserva = autorReserva;
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

    @Override
    public String toString() {
        return "Reserva{" + "dataAlocacao=" + this.getDataAlocacaoString() + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", equipamentos=" + equipamentos + ", sala=" + sala + ", autorReserva=" + autorReserva + '}';
    }

}
