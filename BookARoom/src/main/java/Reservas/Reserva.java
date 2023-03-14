/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservas;

import br.edu.ifnmg.bookaroom.campus.Equipamento;
import br.edu.ifnmg.bookaroom.campus.Funcionario;
import br.edu.ifnmg.bookaroom.campus.Sala;
import java.time.LocalTime;
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

    public Reserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, List<Equipamento> equipamentos, Sala sala, Funcionario autorReserva) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.equipamentos = equipamentos;
        this.sala = sala;
        this.autorReserva = autorReserva;
    }

    public Reserva(Date dataAlocacao, LocalTime horaInicio, LocalTime horaFim, Sala sala, Funcionario autorReserva) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.sala = sala;
        this.autorReserva = autorReserva;
    }

    public Date getDataAlocacao() {
        return dataAlocacao;
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

    
    
}
