/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.campus;

/**
 *
 * @author Jefeson
 */
public class Equipamento {

    private String nome;
    private String patrimonio;

    public Equipamento() {
    }

    public Equipamento(String nome, String patrimonio) {
        this.nome = nome;
        this.patrimonio = patrimonio;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        return "Equipamento{" + "nome=" + nome + ", patrimonio=" + patrimonio + '}';
    }

}
