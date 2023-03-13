/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

import java.util.ArrayList;
import java.util.List;

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
    //</editor-fold>
      
    @Override
    public String toString() {
        return "Predio{" + "nome=" + nome + ", salas=" + salas + '}';
    }
    
    
    
    
}
