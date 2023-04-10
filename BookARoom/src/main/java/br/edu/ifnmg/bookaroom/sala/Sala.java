/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.sala;

import br.edu.ifnmg.bookaroom.bancodedados.BancoDeDados;
import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.predio.Predio;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author dayan
 */
public class Sala {

    private Integer numero;
    private Integer capacidade;
    private Predio predio;

    public Sala() {
    }

    public Sala(Integer numero, Integer capacidade, Predio predio) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.predio = predio;
    }

    public Sala pesquisarSala(int numeroSala, Campus campus) {
        BancoDeDados bd = BancoDeDados.getInstance();
        List<Sala> listaSalas = bd.getListaSalas(campus);
        for (Sala sala: listaSalas){
            if(Objects.equals(numero, sala.getNumero()))
                return sala;
        }
        return null;
    }

    public List<Sala> listarSalas(Campus campus) {
        BancoDeDados bd = BancoDeDados.getInstance();
        return bd.getListaSalas(campus);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Sala{" + "numero=" + numero + ", capacidade=" + capacidade + ", predio=" + predio.getNome() + '}';
    }

   

}
