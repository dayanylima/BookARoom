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
    private ControladorReserva reserva;

    public Console() {
        sc = new Scanner(System.in);
        campus = new ControladorCampus();
        sala = new ControladorSala();
    }

    private Campus verificarNumeroAssociado(int numero, List<Campus> campus) {
        int tamanho = campus.size();
        if (numero <= tamanho) {
            return campus.get(numero - 1);
        }
        return null;
    }

    private Campus selecionarCampusReserva() {
        Campus campusSelecionado;
        do {
            System.out.println("\nCampus cadastrados no sistema: ");

            int numeroAssociado = 1;
            List<Campus> listaCampus = campus.retornarListaDeCampus();

            for (Campus campus : listaCampus) {
                System.out.println(numeroAssociado++ + " - " + campus.getNome());
            }

            System.out.print("\nInforme o número do Campus que deseja selecionar: ");
            int numeroAssociadoDigitado;
            while (true) {
                try {
                    numeroAssociadoDigitado = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Informe o número do Campus que deseja selecionar:");
                    sc.nextLine();
                }
            }
            campusSelecionado = verificarNumeroAssociado(numeroAssociadoDigitado, listaCampus);

            if (campusSelecionado == null) {
                System.out.println("\nPor favor, digite um número válido");
            }
        } while (campusSelecionado == null);
        System.out.println(campusSelecionado);
        return campusSelecionado;
    }

    private static void printOpcoesMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("Escolha uma das opcoes abaixo: ");
        System.out.println("0 - Sair.");
        System.out.println("1 - Fazer reserva de sala.");
        System.out.println("2 - Visualizar disponibilidade de salas.");
        System.out.println("3 - Listar todas as reservas(ATIVAS/INATIVAS).");
        System.out.println("4 - Menu de cadastros.");
    }

    private static void printOpcoesMenuCadastros() {
        System.out.println("\nMENU DE CADASTROS");
        System.out.println("Escolha uma das opcoes abaixo: ");
        System.out.println("0 - Voltar para menu principal.");
        System.out.println("1 - Cadastrar equipamento.");
        System.out.println("2 - Cadastrar funcionário.");
    }

    private void fazerReserva(Campus campus) throws ParseException {
        int resposta1;

        do {
            List<Sala> salasDisponiveis;
            List<Funcionario> funcionarios;
            List<Equipamento> equipamentosdaReserva = new ArrayList<>();
            Funcionario f = new Funcionario();
            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");

            resposta1 = 0;
            String dataReservaString;
            Date dataReserva;
            System.out.println("Informe data que deseja realizar reserva: (dd/MM/yyyy)");
            while (true) {
                try {
                    dataReservaString = sc.nextLine();
                    dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
                    break;
                } catch (Exception e) {
                    System.out.println("Insira uma data válida!");
                    System.out.println("Informe data que deseja realizar reserva: (dd/MM/yyyy)");
                }
            }

            String periodo;
            String[] result;
            LocalTime horaInicio;
            LocalTime horaFim;
            System.out.println("Informe horário de reserva: HH:mm - HH:mm");
            while (true) {
                try {
                    periodo = sc.nextLine();
                    result = periodo.split(" - ");
                    horaInicio = LocalTime.parse(result[0], parser);
                    horaFim = LocalTime.parse(result[1], parser);
                    break;
                } catch (Exception e) {
                    System.out.println("Insira um horário válido!");
                    System.out.println("Informe horário de reserva: HH:mm - HH:mm");
                }
            }

            salasDisponiveis = reserva.consultarSalaDisponivel(campus, dataReserva, horaInicio, horaFim);

            if (salasDisponiveis.isEmpty()) {
                System.out.println("Não há salas disponíveis em dia e horário informado.");
                System.out.println("Deseja informar novo data e horário para reserva? (1-sim ou 2-não)");
                while (true) {
                    try {
                        resposta1 = sc.nextInt();
                        sc.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("Informe uma opção válida!");
                        System.out.println("Deseja informar novo data e horário para reserva? (1-sim ou 2-não)");
                        sc.nextLine();
                    }
                }

            } else {
                System.out.println("SALAS DISPONÍVEIS: ");
                for (Sala sala : salasDisponiveis) {
                    System.out.println(sala);
                }
                System.out.println("Informe o número da sala que deseja realizar reserva: ");
                int num;
                Sala salaSelecionada;
                while (true) {
                    try {
                        num = sc.nextInt();
                        salaSelecionada = sala.pesquisarSala(num, campus);
                        if (salaSelecionada == null)
                            throw new Exception();
                        sc.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("Informe um número válido!");
                        System.out.println("Informe o número da sala que deseja realizar reserva: ");
                        sc.nextLine();
                    }
                }
                funcionarios = campus.getFuncionarios();
                System.out.println("Funcionários do Campus: ");
                listarFuncionario(funcionarios);
                System.out.println("Informe nome de funcionario para adicionar a reserva:");
                String nome;
                while (true) {
                    try {
                        nome = sc.nextLine();
                        f = pesquisarFuncionario(nome, funcionarios);
                        if (f == null)
                            throw new Exception();
                        break;
                    } catch (Exception e) {
                        System.out.println("Informe um nome válido!");
                        System.out.println("Informe nome de funcionario para adicionar a reserva:");
                    }
                }
                int resposta2;
                System.out.println("Deseja incluir equipamentos em reserva? (1-sim ou 2-não)");
                while (true) {
                    try {
                        resposta2 = sc.nextInt();
                        if (!(resposta2 == 1 || resposta2 == 2))
                            throw new Exception();
                        sc.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("Informe uma opção válida!");
                        System.out.println("Deseja incluir equipamentos em reserva? (1-sim ou 2-não)");
                        sc.nextLine();
                    }
                }
                if (resposta2 == 1) {
                    List<Equipamento> equipamentosDisponiveis = reserva.consultarEquipamentoDisponivel(campus,
                            dataReserva, horaInicio, horaFim);

                    System.out.println("Equipamentos disponíveis: ");
                    listarEquipamentos(equipamentosDisponiveis);

                    int resposta3;
                    do {
                        System.out.print("Informe código de equipamento que deseja adicionar a reserva: ");
                        String codigo;
                        Equipamento e;
                        while (true) {
                            try {
                                codigo = sc.next();
                                e = pesquisarEquipamento(codigo, equipamentosDisponiveis);
                                if (e == null)
                                    throw new Exception();
                                sc.nextLine();
                                break;
                            } catch (Exception exp) {
                                System.out.println("Código de equipamento não existe!");
                                System.out.print("Informe código de equipamento que deseja adicionar a reserva: ");
                            }
                        }

                        equipamentosdaReserva.add(pesquisarEquipamento(codigo, equipamentosDisponiveis));
                        System.out.println(e.getNome() + " adicionado à reserva.");

                        System.out.println("Deseja adicionar mais equipamentos à reserva: (1-sim ou 2-não)");
                        while (true) {
                            try {
                                resposta3 = sc.nextInt();
                                if (!(resposta3 == 1 || resposta3 == 2))
                                    throw new Exception();
                                sc.nextLine();
                                break;
                            } catch (Exception exp) {
                                System.out.println("Informe uma opção válida!");
                                System.out.println("Deseja adicionar mais equipamentos à reserva: (1-sim ou 2-não)");
                                sc.nextLine();
                            }
                        }
                    } while (resposta3 == 1);
                    reserva.fazerReservaComEquipamento(campus, dataReserva, horaInicio, horaFim, equipamentosdaReserva,
                            salaSelecionada, f);

                } else {
                    reserva.fazerReserva(campus, dataReserva, horaInicio, horaFim, salaSelecionada, f);
                }
                System.out.println("Reserva realizada com sucesso!!!");
                break;

            }
        } while (resposta1 == 1);

    }

    private void consultarDisponibilidadeDeSalas(Campus campus) throws ParseException {
        //
        int resposta1;
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
        List<Sala> salasDisponiveis = new ArrayList<>();

        do {
            String dataReservaString;
            Date dataReserva;
            System.out.println("Informe data que deseja realizar pesquisa: (dd/MM/yyyy)");
            while (true) {
                try {
                    dataReservaString = sc.nextLine();
                    dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
                    break;
                } catch (Exception e) {
                    System.out.println("Insira uma data válida!");
                    System.out.println("Informe data que deseja realizar pesquisa: (dd/MM/yyyy)");
                }
            }

            String periodo;
            String[] result;
            LocalTime horaInicio;
            LocalTime horaFim;
            System.out.println("Informe horário de pesquisa: HH:mm - HH:mm");
            while (true) {
                try {
                    periodo = sc.nextLine();
                    result = periodo.split(" - ");
                    horaInicio = LocalTime.parse(result[0], parser);
                    horaFim = LocalTime.parse(result[1], parser);
                    break;
                } catch (Exception e) {
                    System.out.println("Insira um horário válido!");
                    System.out.println("Informe horário de reserva: HH:mm - HH:mm");
                }
            }
            salasDisponiveis = reserva.consultarSalaDisponivel(campus, dataReserva, horaInicio, horaFim);
            if (salasDisponiveis.isEmpty()) {
                System.out.println("Não há salas disponíveis em dia e período informado.");
                System.out.println("Deseja informar nova data e período para consulta? (1-sim ou 2-não)");
                while (true) {
                    try {
                        resposta1 = sc.nextInt();
                        if (!(resposta1 == 1 || resposta1 == 2))
                            throw new Exception();
                        sc.nextLine();
                        break;
                    } catch (Exception exp) {
                        System.out.println("Informe uma opção válida!");
                        System.out.println("Deseja informar nova data e período para consulta? (1-sim ou 2-não)");
                        sc.nextLine();
                    }
                }
            } else {
                System.out.println("SALAS DISPONÍVEIS: ");
                for (Sala sala : salasDisponiveis) {
                    System.out.println(sala);
                }
                break;
            }
        } while (resposta1 == 1);

    }

    private void consultarReservasUsuarios(Campus campus) throws ParseException {
        List<Reserva> reservasAtivas = reserva.listarReservasAtivas(campus);
        List<Reserva> reservasInativas = reserva.listarReservasInativas(campus);
        System.out.println("*Note que os horários de aulas não são exibidos aqui*");
        System.out.println("Reservas ATIVAS");
        for (Reserva r : reservasAtivas) {
            if (r.getAutorReserva().getNome() != "RESERVADO_AULA") {
                System.out.println(r);
            }
        }

        System.out.println("Reservas INATIVAS");
        for (Reserva r : reservasInativas) {
            if (r.getAutorReserva().getNome() != "RESERVADO_AULA") {
                System.out.println(r);
            }
        }
    }

    private void cadastrarFuncionario(Campus campus) throws ParseException {
        String nome, cargo, ramal;
        System.out.println("Informe nome de funcionário(a): ");
        nome = sc.nextLine();

        System.out.println("Informe cargo de funcionário: ");
        cargo = sc.nextLine();

        System.out.println("Informe ramal de funcionário: ");
        ramal = sc.nextLine();

        Funcionario funcionario = new Funcionario(nome, cargo, ramal);
        campus.getFuncionarios().add(funcionario);
        System.out.println("Funcionário(a) " + funcionario.getNome() + " cadastrado(a) com sucesso.");

    }

    private void cadastrarEquipamento(Campus campus) throws ParseException {
        String nome, patrimonio;
        System.out.println("Informe nome de equipamento: ");
        nome = sc.nextLine();
        System.out.println("Informe o patrimonio do equipamento: ");
        patrimonio = sc.nextLine();

        Equipamento equipamento = new Equipamento(nome, patrimonio);
        campus.getEquipamentos().add(equipamento);
        System.out.println("Equipamento " + equipamento.getNome() + " de código " + equipamento.getCodigo()
                + " cadastrado com sucesso.");
    }

    public void start() throws ParseException {
        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");
        int opcao;
        Campus campus = this.selecionarCampusReserva();
        reserva = new ControladorReserva();

        do {
            printOpcoesMenu();
            System.out.print("Digite sua opção: ");
            while(true){
                try{
                    opcao = sc.nextInt();
                    sc.nextLine();
                    break;
                }catch (Exception e){
                    System.out.println("Insira uma opção válida!"); 
                    System.out.print("Digite sua opção: ");
                    sc.nextLine();
                }
            }
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
                case 4:
                    int op;
                    do {
                        printOpcoesMenuCadastros();
                        System.out.println("Digite opção: ");
                        while(true){
                            try{
                                op = sc.nextInt();
                                sc.nextLine();
                                break;
                            }catch (Exception e){
                                System.out.println("Insira uma opção válida!"); 
                                System.out.print("Digite sua opção: ");
                                sc.nextLine();
                            }
                        }
                        if (op == 1) {
                            cadastrarEquipamento(campus);
                        }
                        if (op == 2) {
                            cadastrarFuncionario(campus);
                        }
                        if (op == 0) {
                            break;
                        }

                    } while (op != 0);

                    break;
                case 0:
                    break;
            }

        } while (opcao != 0);

    }

}
