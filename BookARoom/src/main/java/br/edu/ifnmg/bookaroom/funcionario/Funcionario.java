/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.funcionario;

/**
 *
 * @author Jefeson
 */
public class Funcionario {

    private String nome;
    private String cargo;
    private String ramal;

    public Funcionario() {
    }

    public Funcionario(String nome, String cargo, String ramal) {
        this.nome = nome;
        this.cargo = cargo;
        this.ramal = ramal;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", cargo=" + cargo + ", ramal=" + ramal + '}';
    }

}
