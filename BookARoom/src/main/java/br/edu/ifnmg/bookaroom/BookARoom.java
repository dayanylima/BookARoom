/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.campus.Endereco;
import br.edu.ifnmg.bookaroom.campus.Equipamento;
import br.edu.ifnmg.bookaroom.campus.Funcionario;
import br.edu.ifnmg.bookaroom.campus.Predio;
import br.edu.ifnmg.bookaroom.campus.Sala;
import br.edu.ifnmg.bookaroom.reservas.ControladorReservas;
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
    private static List<Reserva> listaReservas = new ArrayList<>();

    public static void menu() {
        System.out.println("Escolha uma das opcoes abaixo: \n");
        System.out.println("0 - Sair.\n");
        System.out.println("1 - Fazer reserva de sala.\n");
        System.out.println("2 - Visualizar disponibilidade de salas.");
    }

    public static List<Reserva> retornarReservasDeCampus(List<Reserva> lista, Campus c) {

        List<Reserva> reservas = new ArrayList<>();
        for (Reserva r : lista) {
            if (r.getCampus().getNome().equalsIgnoreCase(c.getNome())) {
                reservas.add(r);
            }
        }

        return reservas;
    }

    public static Campus pesquisarCampus(String campusNome) {

        for (Campus c : listaCampus) {
            if (c.getNome().equalsIgnoreCase(campusNome)) {
                return c;
            }
        }

        return null;
    }

    public static Sala pesquisarSala(Integer numero, List<Sala> salas) {

        for (Sala s : salas) {
            if (s.getNumero() == numero) {
                return s;
            }
        }

        return null;
    }

    public static Equipamento pesquisarEquipamento(Long codigo, List<Equipamento> equipamentos) {

        for (Equipamento e : equipamentos) {
            if (Objects.equals(e.getCodigo(), codigo)) {
                return e;
            }
        }

        return null;
    }

    public static void listarEquipamentos(List<Equipamento> equipamentos) {

        for (Equipamento e : equipamentos) {

            System.out.println(e.toString());
        }

    }

    public static void fazerReserva(ControladorReservas controlador) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Sala> salasDisponiveis = new ArrayList<>();
        List<Equipamento> equipamentosdaReserva = new ArrayList<>();
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

                Reserva reserva = controlador.fazerNovaReserva(dataReserva, horaInicio, horaFim, equipamentosdaReserva, salaSelecionada, null);

                listaReservas.add(reserva);
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
        //</editor-fold>

        listaCampus.add(campus1);
        listaCampus.add(campus2);

        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");

        int opcao;

        System.out.println("Por favor informe campus que deseja realizar reserva de sala:\n");

        for (Campus c : listaCampus) {
            System.out.println(c.getNome());
        }

        System.out.print("Digite: ");
        String campusNome = sc.nextLine();

        Campus campusSelecionado = new Campus();

        campusSelecionado = pesquisarCampus(campusNome);

        while (campusSelecionado == null) {

            System.out.print("Digite nome de campus válido: ");
            campusNome = sc.nextLine();
            campusSelecionado = pesquisarCampus(campusNome);
        }

        ControladorReservas controlador = new ControladorReservas(campusSelecionado);
        System.out.println(campusSelecionado.toString());

        do {

//            System.out.println("Por favor informe campus que deseja realizar reserva de sala:\n");
//
//            for (Campus c : listaCampus) {
//                System.out.println(c.getNome());
//            }
//
//            System.out.print("Digite: ");
//            String campusNome = sc.nextLine();
//
//            Campus campusSelecionado = new Campus();
//
//            campusSelecionado = pesquisarCampus(campusNome);
//
//            while (campusSelecionado == null) {
//
//                System.out.print("Digite nome de campus válido: ");
//                campusNome = sc.nextLine();
//                campusSelecionado = pesquisarCampus(campusNome);
//            }
//
//            ControladorReservas controlador = new ControladorReservas(campusSelecionado);
//
//            System.out.println(campusSelecionado.toString());
            menu();

            System.out.print("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    fazerReserva(controlador);
                    break;

            }

        } while (opcao != 0);
//        
//        System.out.println(predio.getSalasDisponiveis());
//        ControladorReservas reservas = new ControladorReservas();
//
//        Date data = new Date();
//        Funcionario funcionario = new Funcionario("Jefeson", "Professor", "123");
//        LocalTime horaInicio = LocalTime.parse("15:00:00");
//        LocalTime horaFim = LocalTime.parse("17:00:00");
//        reservas.fazerNovaReserva(data, horaInicio, horaFim, sala1, funcionario);
//
//        reservas.fazerNovaReserva(data, LocalTime.parse("05:18:23"), LocalTime.parse("06:18:23"), sala, funcionario);
//        List<Reserva> r = reservas.getReservas();
//        System.out.println(reservas.getReservas());
//        LocalTime horaInicio2 = LocalTime.parse("16:00:01");
//        LocalTime horaFim2 = LocalTime.parse("16:30:00");
//
//        List<Reserva> r = reservas.consultarSalaIndisponivel(data, horaInicio2, horaFim2);
//        System.out.println(r);
//
//        List<Reserva> ri = reservas.consultarSalaDisponivel(data, horaInicio, horaFim);
//        System.out.println(ri);
//        LocalTime horaInicio2 = LocalTime.parse("23:59:59");
//        System.out.println(horaInicio2.toSecondOfDay());
    }
}
