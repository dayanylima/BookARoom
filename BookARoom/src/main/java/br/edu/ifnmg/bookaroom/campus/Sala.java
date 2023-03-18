/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

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
