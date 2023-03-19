/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

import br.edu.ifnmg.bookaroom.predio.Predio;
import br.edu.ifnmg.bookaroom.endereco.Endereco;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
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
    private List<Equipamento> equipamentos;

    public Campus() {
        predios = new ArrayList<>();
        funcionarios = new ArrayList<>();
        equipamentos = new ArrayList<>();
    }

    public Campus(String nome, Endereco endereco, List<Predio> predios, List<Funcionario> funcionarios, List<Equipamento> equipamentos) {
        this();
        this.nome = nome;
        this.endereco = endereco;
        this.predios = predios;
        this.funcionarios = funcionarios;
        this.equipamentos = equipamentos;
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

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        return "Campus{" + "nome=" + nome + ", endereco=" + endereco + '}';
    }

}
