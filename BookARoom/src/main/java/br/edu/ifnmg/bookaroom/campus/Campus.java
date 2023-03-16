/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

import br.edu.ifnmg.bookaroom.reservas.Reserva;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dayan
 */
public class Campus {

    private String nome;
    private Endereco endereco;
    private List<Predio> predios;
    private List<Funcionario> funcionarios;
    private List<Reserva> reservas;

    public Campus() {
        predios = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Campus(String nome, Endereco endereco, List<Predio> predios, List<Funcionario> funcionarios) {
        this.nome = nome;
        this.endereco = endereco;
        this.predios = predios;
        this.funcionarios = funcionarios;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    //</editor-fold>
    @Override
    public String toString() {
        return "Campus{" + "nome=" + nome + ", endereco=" + endereco + '}';
    }

}
