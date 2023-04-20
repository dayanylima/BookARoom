package br.edu.ifnmg.bookaroom.interfaceUsuario;

import static br.edu.ifnmg.bookaroom.controladores.ControladorEquipamento.listarEquipamentos;
import static br.edu.ifnmg.bookaroom.controladores.ControladorEquipamento.pesquisarEquipamento;
import static br.edu.ifnmg.bookaroom.controladores.ControladorFuncionario.listarFuncionario;
import static br.edu.ifnmg.bookaroom.controladores.ControladorFuncionario.pesquisarFuncionario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.edu.ifnmg.bookaroom.bancodedados.BancoDeDados;
import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.controladores.ControladorCampus;
import br.edu.ifnmg.bookaroom.controladores.ControladorReserva;
import br.edu.ifnmg.bookaroom.controladores.ControladorSala;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import br.edu.ifnmg.bookaroom.sala.Sala;


public class Console {
    private Scanner sc;
    private ControladorCampus campus;
    private ControladorSala sala;
    private ControladorReserva reserva; //chjamar de reserva dps

    public Console() {
        sc = new Scanner(System.in);
        campus = new ControladorCampus();
        sala = new ControladorSala();

    }

    private Campus verificarNumeroAssociado(int numero, List<Campus> campus) {
        int tamanho = campus.size();
        if (numero <= tamanho)
            return campus.get(numero - 1);
        return null;
    }

    private Campus selecionarCampusReserva() {
        Campus campusSelecionado;
        do {
            System.out.println("\nCampus cadastrados em sistema:");

            int numeroAssociado = 1;
            List<Campus> listaCampus = campus.listarCampus();

            for (Campus campus : listaCampus) {
                System.out.println(numeroAssociado++ + " - " + campus.getNome());
            }

            System.out.print("\nInforme o número do Campus que deseja selecionar: ");

            int numeroAssociadoDigitado = sc.nextInt();
            sc.nextLine(); 
            campusSelecionado = verificarNumeroAssociado(numeroAssociadoDigitado, listaCampus);

            if (campusSelecionado == null)
                System.out.println("\nPor favor, digite um número válido");
        }
        while (campusSelecionado == null);
        System.out.println(campusSelecionado);
        return campusSelecionado;
    }


    private static void printOpcoesMenu() {
        System.out.println("Escolha uma das opcoes abaixo: \n");
        System.out.println("0 - Sair.\n");
        System.out.println("1 - Fazer reserva de sala.\n");
        System.out.println("2 - Visualizar disponibilidade de salas.\n");
        System.out.println("3 - Listar todas as reservas(ATIVAS/INATIVAS).\n");
    }

    public void fazerReserva(Campus campus) throws ParseException {
        int resposta1;

        do {
            List<Sala> salasDisponiveis;
            List<Funcionario> funcionarios;
            List<Equipamento> equipamentosdaReserva = new ArrayList<>();
            Funcionario f = new Funcionario();
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");

            resposta1 = 0;
            System.out.println("Informe dia que deseja realizar reserva: (dd/MM/yyyy)");
            String dataReservaString = sc.nextLine();
            System.out.println("aaaa");
            Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);

            System.out.println("Informe horário de reserva: HH:mm - HH:mm");
            String periodo = sc.nextLine();

            String[] result = periodo.split(" - ");

            LocalTime horaInicio = LocalTime.parse(result[0], parser);

            LocalTime horaFim = LocalTime.parse(result[1], parser);

            salasDisponiveis = reserva.consultarSalaDisponivel(campus, dataReserva, horaInicio, horaFim);

            if (salasDisponiveis.isEmpty()) {
                System.out.println("Não há salas disponíveis em dia e horário informado.");
                System.out.println("Deseja informar novo data e horário para reserva? (1-sim ou 2-não)");
                resposta1 = sc.nextInt();
                sc.nextLine(); 

            } else {

                //exibirSalas(salasDisponiveis);
                for (Sala sala: salasDisponiveis){
                    System.out.println(sala);
                }

                System.out.println("Informe o número da sala que deseja realizar reserva: ");
                int num = sc.nextInt();
                sc.nextLine(); 

                //Sala salaSelecionada = pesquisarSala(num, salasDisponiveis);
                Sala salaSelecionada = sala.pesquisarSala(num, campus);
                System.out.println(salaSelecionada);
                funcionarios = campus.getFuncionarios();
                System.out.println("Funcionários do Campus: ");
                listarFuncionario(funcionarios);

                //Não exclui

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
                sc.nextLine(); 
                if (resposta2 == 1) {
                    List<Equipamento> equipamentosDisponiveis = reserva.consultarEquipamentoDisponivel(campus, dataReserva, horaInicio, horaFim);

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
                        sc.nextLine(); 

                    } while (resposta3 == 1);
                    reserva.fazerReservaComEquipamento(campus, dataReserva, horaInicio, horaFim, equipamentosdaReserva, salaSelecionada, f);

                }else{
                    reserva.fazerReserva(campus, dataReserva, horaInicio, horaFim, salaSelecionada, f);
                    System.out.println("Reserva realizada. Informações: " + reserva.toString());
                }

               // Reserva reserva = reserva.fazerReservaComEquipamento(campus, dataReserva, horaInicio, horaFim, equipamentosdaReserva, salaSelecionada, f);

                //System.out.println("Reserva realizada. Informações: " + reserva.toString());
                break;

            }
        } while (resposta1 == 1);

    }


    public void consultarDisponibilidadeDeSalas(Campus campus) throws ParseException {
//
        int resposta1;

        do {
            List<Sala> salasDisponiveis = new ArrayList<>();
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println("Informe data que deseja realizar pesquisa: (dd/MM/yyyy)");
            String dataReservaString = sc.nextLine();
            Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);

            System.out.println("Informe horário de pesquisa: HH:mm - HH:mm");
            String periodo = sc.nextLine();
            String[] result = periodo.split(" - ");

            LocalTime horaInicio = LocalTime.parse(result[0], parser);
            LocalTime horaFim = LocalTime.parse(result[1], parser);

            salasDisponiveis = reserva.consultarSalaDisponivel(campus, dataReserva, horaInicio, horaFim);
            if (salasDisponiveis.isEmpty()) {
                System.out.println("Não há salas disponíveis em dia e período informado.");
                System.out.println("Deseja informar nova data e período para consulta? (1-sim ou 2-não)");
                resposta1 = sc.nextInt();
                sc.nextLine(); 

            } else {

                System.out.println("SALAS DISPONÍVEIS: ");
                for(Sala sala: salasDisponiveis){
                    System.out.println(sala);
                }
                break;
            }

        } while (resposta1 == 1);
    }


public void consultarReservasUsuarios(Campus campus) throws ParseException {
        List<Reserva> reservasAtivas = reserva.listarReservasAtivas(campus);
        List<Reserva> reservasInativas = reserva.listarReservasInativas(campus);
        System.out.println("*Note que os horários de aulas não são exibidos aqui*");
        System.out.println("Reservas ATIVAS");
        for (Reserva r : reservasAtivas) {
            if(r.getAutorReserva().getNome() != "RESERVADO_AULA"){
                System.out.println(r);
            }
        }

        System.out.println("Reservas INATIVAS");
        for (Reserva r : reservasInativas) {
            if(r.getAutorReserva().getNome() != "RESERVADO_AULA"){
                System.out.println(r);
            }
        }
    }


    public void start() throws ParseException {
        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");
        int opcao;
        //String sair = "n";
        Campus campus = this.selecionarCampusReserva();
        reserva = new ControladorReserva();
        BancoDeDados.getInstance().definirHorarioAulas(campus);

        do {
            printOpcoesMenu();
            System.out.print("Digite sua opção: ");
            //System.out.println("teste");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    fazerReserva(campus);
                    break;
                case 2:
                    consultarDisponibilidadeDeSalas(campus);
                    break;
                case 3:
                    consultarReservasUsuarios(campus);
                    break;
                case 0:
                    break;
            }

        } while (opcao != 0);


}

}