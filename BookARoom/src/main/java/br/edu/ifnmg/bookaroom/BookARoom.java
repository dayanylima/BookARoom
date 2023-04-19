/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import java.text.ParseException;

import br.edu.ifnmg.bookaroom.controladores.ControladorReserva;
import br.edu.ifnmg.bookaroom.interfaceUsuario.Console;

/**
 *
 * @author dayan
 */
public class BookARoom {

//    public static void fazerReserva(ControladorReserva controlador) throws ParseException {
//        int resposta1;
//
//        do {
//            Scanner sc = new Scanner(System.in);
//            List<Sala> salasDisponiveis = new ArrayList<>();
//            List<Equipamento> equipamentosdaReserva = new ArrayList<>();
//            List<Funcionario> funcionarios = new ArrayList<>();
//            Funcionario f = new Funcionario();
//            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
//
//            resposta1 = 0;
//            System.out.println("Informe dia que deseja realizar reserva: (dd/MM/yyyy)");
//            String dataReservaString = sc.nextLine();
//            Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
//
//            System.out.println("Informe horário de reserva: HH:mm - HH:mm");
//            String periodo = sc.nextLine();
//
//            String[] result = periodo.split(" - ");
//
//            LocalTime horaInicio = LocalTime.parse(result[0], parser);
//
//            LocalTime horaFim = LocalTime.parse(result[1], parser);
//
//            salasDisponiveis = controlador.consultarSalaDisponivel(dataReserva, horaInicio, horaFim);
//
//            if (salasDisponiveis.isEmpty()) {
//                System.out.println("Não há salas disponíveis em dia e horário informado.");
//                System.out.println("Deseja informar novo data e horário para reserva? (1-sim ou 2-não)");
//                resposta1 = sc.nextInt();
//
//            } else {
//
//                exibirSalas(salasDisponiveis);
//
//                System.out.println("Informe o número da sala que deseja realizar reserva: ");
//                int num = sc.nextInt();
//
//                Sala salaSelecionada = pesquisarSala(num, salasDisponiveis);
//
//                funcionarios = controlador.getCampus().getFuncionarios();
//                System.out.println("Funcionários do Campus: ");
//                listarFuncionario(funcionarios);
//
//                //Não exclui
//                sc.nextLine();
//
//                do {
//                    System.out.println("Informe nome de funcionario para adicionar a reserva:");
//                    String nome = sc.nextLine();
//                    f = pesquisarFuncionario(nome, funcionarios);
//                    if (f == null) {
//                        System.out.println("Informe nome válido!!!");
//                    }
//
//                } while (f == null);
//
//                System.out.println("Deseja incluir equipamentos em reserva? (1-sim ou 2-não)");
//                int resposta2 = sc.nextInt();
//                if (resposta2 == 1) {
//                    List<Equipamento> equipamentosDisponiveis = controlador.consultarEquipamentoDisponivel(dataReserva, horaInicio, horaFim);
//
//                    System.out.println("Equipamentos disponíveis: ");
//                    listarEquipamentos(equipamentosDisponiveis);
//
//                    int resposta3;
//                    do {
//                        System.out.print("Informe código de equipamento que deseja adicionar a reserva: ");
//                        Long codigo = sc.nextLong();
//                        Equipamento e = pesquisarEquipamento(codigo, equipamentosDisponiveis);
//                        if (e == null) {
//                            System.out.println("Código informado não existe.");
//                        } else {
//
//                            equipamentosdaReserva.add(pesquisarEquipamento(codigo, equipamentosDisponiveis));
//                            System.out.println(e.getNome() + " adicionado à reserva.");
//                        }
//                        System.out.println("Deseja adicionar mais equipamentos à reserva: (1-sim ou 2-não)");
//                        resposta3 = sc.nextInt();
//
//                    } while (resposta3 == 1);
//                }
//
//                Reserva reserva = controlador.fazerNovaReserva(dataReserva, horaInicio, horaFim, equipamentosdaReserva, salaSelecionada, f);
//
//                System.out.println("Reserva realizada. Informações: " + reserva.toString());
//                break;
//
//            }
//        } while (resposta1 == 1);
//
//    }

    public static void consultarDisponibilidadeDeSalas(ControladorReserva controlador) throws ParseException {
//
//        int resposta1;
//
//        do {
//
//            List<Sala> salasDisponiveis = new ArrayList<>();
//            Scanner sc = new Scanner(System.in);
//            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
//            System.out.println("Informe data que deseja realizar pesquisa: (dd/MM/yyyy)");
//            String dataReservaString = sc.nextLine();
//            Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
//
//            System.out.println("Informe horário de pesquisa: HH:mm - HH:mm");
//            String periodo = sc.nextLine();
//
//            String[] result = periodo.split(" - ");
//
//            LocalTime horaInicio = LocalTime.parse(result[0], parser);
//            LocalTime horaFim = LocalTime.parse(result[1], parser);
//
//            salasDisponiveis = controlador.consultarSalaDisponivel(dataReserva, horaInicio, horaFim);
//            if (salasDisponiveis.isEmpty()) {
//                System.out.println("Não há salas disponíveis em dia e período informado.");
//                System.out.println("Deseja informar nova data e período para consulta? (1-sim ou 2-não)");
//                resposta1 = sc.nextInt();
//
//            } else {
//
//                System.out.println("SALAS DISPONÍVEIS: ");
//                exibirSalas(salasDisponiveis);
//                break;
//            }
//
//        } while (resposta1 == 1);

    }

    // public static void consultarReservasUsuarios(ControladorReserva controlador) throws ParseException {
    //     List<Reserva> reservas = controlador.getReservas();

    //     List<Reserva> reservasAtivas = new ArrayList<>();
    //     List<Reserva> reservasInativas = new ArrayList<>();

    //     Date dataAtual = new Date();
    //     LocalTime horaAtual = LocalDateTime.ofInstant(dataAtual.toInstant(),
    //             ZoneId.systemDefault()).toLocalTime();

    //     for (Reserva r : reservas) {
    //         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //         dataAtual = sdf.parse(sdf.format(dataAtual));
    //         int situacaoDiaReserva = r.getDataAlocacao().compareTo(dataAtual);

    //         System.out.println(situacaoDiaReserva);
    //         boolean horaReservaTerminaFuturo = r.getHoraFim().compareTo(horaAtual) > 0;
    //         if (situacaoDiaReserva == 0 && horaReservaTerminaFuturo || situacaoDiaReserva > 0) {
    //             reservasAtivas.add(r);
    //         } else {
    //             reservasInativas.add(r);
    //         }
    //     }

    //     System.out.println("Reservas ATIVAS");
    //     for (Reserva r : reservasAtivas) {
    //         System.out.println(r);
    //     }

    //     System.out.println("Reservas INATIVAS");
    //     for (Reserva r : reservasInativas) {
    //         System.out.println(r);
    //     }
    // }

    public static void main(String[] args) throws ParseException {

        Console console = new Console();
        console.start();
        System.exit(0);
//        Scanner sc = new Scanner(System.in);
//
//        BancoDeDados bd = new BancoDeDados();
//        bd.iniciarObjetos();
//
//        for (Campus c : bd.getListaCampus()) {
//            ControladorReserva controlador = new ControladorReserva();
//            controlador.setCampus(c);
//            controladores.add(controlador);
//        }
//
//        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");
//
//        int opcao;
//        String sair = "n";
//
//        do {
//            System.out.println("Por favor informe campus que deseja realizar reserva de sala:\n");
//
//            listarCampus(bd.getListaCampus());
//
//
//            System.out.print("Digite: ");
//            String campusNome = sc.nextLine();
//
//            Campus campusSelecionado = new Campus();
//
//            campusSelecionado = pesquisarCampus(campusNome, bd.getListaCampus());
//
//            while (campusSelecionado == null) {
//
//                System.out.print("Digite nome de campus válido: ");
//                campusNome = sc.nextLine();
//                campusSelecionado = pesquisarCampus(campusNome, bd.getListaCampus());
//            }
//
//            ControladorReserva controlador = new ControladorReserva();
//            System.out.println(campusSelecionado.toString());
//            int i = 0;
//            for (ControladorReserva ctrls : controladores) {
//
//                if (controladores.get(i).getCampus().equals(campusSelecionado)) {
//                    controlador = ctrls;
//                }
//                i++;
//            }
//
//            do {
//                menu();
//
//                System.out.print("Opção: ");
//                opcao = sc.nextInt();
//
//                switch (opcao) {
//
//                    case 1:
//                        fazerReserva(controlador);
//                        break;
//                    case 2:
//                        consultarDisponibilidadeDeSalas(controlador);
//                        break;
//                    case 3:
//                        consultarReservasUsuarios(controlador);
//                        break;
//                    case 0:
//                        break;
//                    case 10:
//                        sair = "s";
//                        break;
//
//                }
//
//                if (sair.equalsIgnoreCase("s")) {
//                    break;
//                }
//
//            } while (opcao != 0);
//        } while (sair.equalsIgnoreCase("n"));

    }
}
