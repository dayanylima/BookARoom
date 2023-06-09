/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.predio;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifnmg.bookaroom.sala.Sala;

/**
 *
 * @author dayan
 */
public class Predio {

    private String nome;
    private List<Sala> salas;

    public Predio() {
        salas = new ArrayList<>();
    }

    public Predio(String nome, List<Sala> salas) {
        this.nome = nome;
        this.salas = salas;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Sala> getSalasDisponiveis() {
        return salas;
    }

    @Override
    public String toString() {
        return "Predio{" + "nome=" + nome + ", salas=" + salas + '}';
    }

}
