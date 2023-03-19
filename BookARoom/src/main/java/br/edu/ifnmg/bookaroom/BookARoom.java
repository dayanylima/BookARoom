/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import br.edu.ifnmg.bookaroom.campus.Campus;
import static br.edu.ifnmg.bookaroom.campus.ControladorCampus.listarCampus;
import static br.edu.ifnmg.bookaroom.campus.ControladorCampus.pesquisarCampus;
import static br.edu.ifnmg.bookaroom.equipamento.ControladorEquipamento.listarEquipamentos;
import static br.edu.ifnmg.bookaroom.equipamento.ControladorEquipamento.pesquisarEquipamento;
import static br.edu.ifnmg.bookaroom.sala.ControladorSala.pesquisarSala;
import static br.edu.ifnmg.bookaroom.funcionario.ControladorFuncionario.listarFuncionario;
import static br.edu.ifnmg.bookaroom.funcionario.ControladorFuncionario.pesquisarFuncionario;
import br.edu.ifnmg.bookaroom.endereco.Endereco;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.predio.Predio;
import br.edu.ifnmg.bookaroom.sala.Sala;
import br.edu.ifnmg.bookaroom.reservas.ControladorReserva;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author dayan
 */
public class BookARoom {

    private static List<Campus> listaCampus = new ArrayList<>();

    public static void menu() {
        System.out.println("Escolha uma das opcoes abaixo: \n");
        System.out.println("0 - Sair.\n");
        System.out.println("1 - Fazer reserva de sala.\n");
        System.out.println("2 - Visualizar disponibilidade de salas.");
    }

    public static void fazerReserva(ControladorReserva controlador) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Sala> salasDisponiveis = new ArrayList<>();
        List<Equipamento> equipamentosdaReserva = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario f = new Funcionario();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
        int resposta1;

        do {

            resposta1 = 0;
            System.out.println("Informe dia que deseja realizar reserva: (dd/MM/yyyy)");
            String dataReservaString = sc.nextLine();
            Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
            //System.out.println("->" + dataReserva);

            System.out.println("Informe hora de inicio de reserva: (HH:mm)");
            String horaInicioString = sc.nextLine();
            LocalTime horaInicio = LocalTime.parse(horaInicioString, parser);
            //System.out.println("->" + horaInicio);

            System.out.println("Informe hora de fim de reserva: (HH:mm)");
            String horaFimString = sc.nextLine();
            LocalTime horaFim = LocalTime.parse(horaFimString, parser);
            //System.out.println("->" + horaFim);

            salasDisponiveis = controlador.consultarSalaDisponivel(dataReserva, horaInicio, horaFim);

            if (salasDisponiveis == null) {
                System.out.println("Não há salas disponíveis em dia e horário informado.");
                System.out.println("Deseja informar novo data e horário para reserva? (1-sim ou 2-não)");
                resposta1 = sc.nextInt();

            } else {

                for (Sala s : salasDisponiveis) {
                    System.out.println(s.toString());
                }
                System.out.println("Informe o número da sala que deseja realizar reserva: ");
                int num = sc.nextInt();

                Sala salaSelecionada = pesquisarSala(num, salasDisponiveis);

                funcionarios = controlador.getCampus().getFuncionarios();
                System.out.println("Funcionários do Campus: ");
                listarFuncionario(funcionarios);

                //Não exclui
                sc.nextLine();

                do {
                    System.out.println("Informe nome de funcionario para adicionar a reserva:");
                    String nome = sc.nextLine();
                    f = pesquisarFuncionario(nome, funcionarios);
                    if (f == null) {
                        System.out.println("Informe nome válido!!!");
                    }

                } while (f == null);

                System.out.println("Deseja incluir equipamentos em reserva? (1-sim ou 2-não)");
                int resposta2 = sc.nextInt();
                if (resposta2 == 1) {
                    List<Equipamento> equipamentosDisponiveis = controlador.consultarEquipamentoDisponivel(dataReserva, horaInicio, horaFim);

                    System.out.println("Equipamentos disponíveis: ");
                    listarEquipamentos(equipamentosDisponiveis);

                    int resposta3;
                    do {
                        System.out.print("Informe código de equipamento que deseja adicionar a reserva: ");
                        Long codigo = sc.nextLong();
                        Equipamento e = pesquisarEquipamento(codigo, equipamentosDisponiveis);
                        if (e == null) {
                            System.out.println("Código informado não existe.");
                        } else {

                            equipamentosdaReserva.add(pesquisarEquipamento(codigo, equipamentosDisponiveis));
                            System.out.println(e.getNome() + " adicionado à reserva.");
                        }
                        System.out.println("Deseja adicionar mais equipamentos à reserva: (1-sim ou 2-não)");
                        resposta3 = sc.nextInt();

                    } while (resposta3 == 1);
                }

                Reserva reserva = controlador.fazerNovaReserva(dataReserva, horaInicio, horaFim, equipamentosdaReserva, salaSelecionada, f);

                System.out.println("Reserva realizada. Informações: " + reserva.toString());

            }
        } while (resposta1 == 1);

    }

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        //<editor-fold defaultstate="collapsed" desc="CAMPUS MONTES CLAROS">
        Predio predio1 = new Predio();
        predio1.setNome("Predio 1");

        Sala sala1 = new Sala(1, 20, predio1);
        Sala sala2 = new Sala(2, 20, predio1);
        Sala sala3 = new Sala(3, 20, predio1);
        Sala sala4 = new Sala(4, 20, predio1);

        predio1.getSalas().add(sala1);
        predio1.getSalas().add(sala2);
        predio1.getSalas().add(sala3);
        predio1.getSalas().add(sala4);

        Predio predio2 = new Predio();
        predio2.setNome("Predio 2");
        Sala sala5 = new Sala(5, 20, predio2);
        Sala sala6 = new Sala(6, 20, predio2);
        Sala sala7 = new Sala(7, 20, predio2);
        Sala sala8 = new Sala(8, 20, predio2);

        predio2.getSalas().add(sala5);
        predio2.getSalas().add(sala6);
        predio2.getSalas().add(sala7);
        predio2.getSalas().add(sala8);

        Equipamento equipamento1 = new Equipamento(352394L, "Lousa Digital 1", "IFNMG");
        Equipamento equipamento2 = new Equipamento(352395L, "Lousa Digital 2", "IFNMG");
        Equipamento equipamento3 = new Equipamento(352396L, "Notebook Dell", "IFNMG");
        Equipamento equipamento4 = new Equipamento(352381L, "Notebook Acer", "IFNMG");
        Equipamento equipamento5 = new Equipamento(352382L, "Projetor 1", "IFNMG");
        Equipamento equipamento6 = new Equipamento(352383L, "Projetor 2", "IFNMG");

        Endereco endereco1 = new Endereco("Montes Claros", "Village do Lago I", "Rua Dois", 300);

        Funcionario funcionario1 = new Funcionario("Amanda Silva", "Professora", "Educação");
        Funcionario funcionario2 = new Funcionario("Ricardo Rocha", "Professor", "Educação");
        Funcionario funcionario3 = new Funcionario("Sueli Santos", "Professora", "Educação");
        Funcionario funcionario4 = new Funcionario("Antonio Gomes", "Professor", "Educação");

        Campus campus1 = new Campus();
        campus1.setNome("Campus Montes Claros");
        campus1.setEndereco(endereco1);
        campus1.getPredios().add(predio1);
        campus1.getPredios().add(predio2);
        campus1.getEquipamentos().add(equipamento1);
        campus1.getEquipamentos().add(equipamento2);
        campus1.getEquipamentos().add(equipamento3);
        campus1.getEquipamentos().add(equipamento4);
        campus1.getEquipamentos().add(equipamento5);
        campus1.getEquipamentos().add(equipamento6);

        campus1.getFuncionarios().add(funcionario1);
        campus1.getFuncionarios().add(funcionario2);
        campus1.getFuncionarios().add(funcionario3);
        campus1.getFuncionarios().add(funcionario4);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="CAMPUS PIRAPORA"> 
        Predio predio3 = new Predio();
        predio3.setNome("Predio 1");
        Sala sala9 = new Sala(1, 30, predio3);
        Sala sala10 = new Sala(2, 30, predio3);
        Sala sala11 = new Sala(3, 30, predio3);
        Sala sala12 = new Sala(4, 30, predio3);
        Sala sala13 = new Sala(5, 30, predio3);
        Sala sala14 = new Sala(6, 30, predio3);

        predio3.getSalas().add(sala9);
        predio3.getSalas().add(sala10);
        predio3.getSalas().add(sala11);
        predio3.getSalas().add(sala12);
        predio3.getSalas().add(sala13);
        predio3.getSalas().add(sala14);

        Equipamento equipamento7 = new Equipamento(567881L, "Lousa Digital 1", "IFNMG");
        Equipamento equipamento8 = new Equipamento(567882L, "Lousa Digital 2", "IFNMG");
        Equipamento equipamento9 = new Equipamento(567883L, "Notebook Asus", "IFNMG");
        Equipamento equipamento10 = new Equipamento(567884L, "Notebook Lenovo", "IFNMG");
        Equipamento equipamento11 = new Equipamento(567885L, "Projetor 1", "IFNMG");
        Equipamento equipamento12 = new Equipamento(567886L, "Projetor 2", "IFNMG");

        Endereco endereco2 = new Endereco("Pirapora", "Santos Dumont", "Av. Humberto Mallard", 1355);

        Funcionario funcionario5 = new Funcionario("Adriana Silva", "Professora", "Educação");
        Funcionario funcionario6 = new Funcionario("Paulo Rocha", "Professor", "Educação");
        Funcionario funcionario7 = new Funcionario("Laura Santos", "Professora", "Educação");
        Funcionario funcionario8 = new Funcionario("Flávio Gomes", "Professor", "Educação");

        Campus campus2 = new Campus();
        campus2.setNome("Campus Pirapora");
        campus2.setEndereco(endereco2);
        campus2.getPredios().add(predio3);
        campus2.getEquipamentos().add(equipamento7);
        campus2.getEquipamentos().add(equipamento8);
        campus2.getEquipamentos().add(equipamento9);
        campus2.getEquipamentos().add(equipamento10);
        campus2.getEquipamentos().add(equipamento11);
        campus2.getEquipamentos().add(equipamento12);

        campus2.getFuncionarios().add(funcionario5);
        campus2.getFuncionarios().add(funcionario6);
        campus2.getFuncionarios().add(funcionario7);
        campus2.getFuncionarios().add(funcionario8);

        //</editor-fold>
        listaCampus.add(campus1);
        listaCampus.add(campus2);

        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");

        int opcao;

        System.out.println("Por favor informe campus que deseja realizar reserva de sala:\n");

        listarCampus(listaCampus);

        System.out.print("Digite: ");
        String campusNome = sc.nextLine();

        Campus campusSelecionado = new Campus();

        campusSelecionado = pesquisarCampus(campusNome, listaCampus);

        while (campusSelecionado == null) {

            System.out.print("Digite nome de campus válido: ");
            campusNome = sc.nextLine();
            campusSelecionado = pesquisarCampus(campusNome, listaCampus);
        }

        ControladorReserva controlador = new ControladorReserva(campusSelecionado);
        System.out.println(campusSelecionado.toString());

        do {
            menu();

            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    fazerReserva(controlador);
                    break;

            }

        } while (opcao != 0);

    }
}
