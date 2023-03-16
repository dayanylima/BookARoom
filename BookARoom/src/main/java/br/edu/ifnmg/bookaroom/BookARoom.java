/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package br.edu.ifnmg.bookaroom;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.campus.Endereco;
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

    public static Campus pesquisarCampus(String campusNome) {

        for (Campus c : listaCampus) {
            if (c.getNome().equalsIgnoreCase(campusNome)) {
                return c;
            }
        }

        return null;
    }

    //public static void fazerReserva() {
//        Sala sala1 = new Sala(1, 20);
//        Sala sala2 = new Sala(2, 20);
//        Sala sala3 = new Sala(3, 20);
//        Sala sala4 = new Sala(4, 20);
//
//        Predio predio1 = new Predio();
//        predio1.setNome("Predio 1");
//        predio1.getSalas().add(sala1);
//        predio1.getSalas().add(sala2);
//        predio1.getSalas().add(sala3);
//        predio1.getSalas().add(sala4);
//
//        Sala sala5 = new Sala(5, 20);
//        Sala sala6 = new Sala(6, 20);
//        Sala sala7 = new Sala(7, 20);
//        Sala sala8 = new Sala(8, 20);
//
//        Predio predio2 = new Predio();
//        predio2.setNome("Predio 2");
//        predio2.getSalas().add(sala5);
//        predio2.getSalas().add(sala6);
//        predio2.getSalas().add(sala7);
//        predio2.getSalas().add(sala8);
//
//        Endereco endereco1 = new Endereco("Montes Claros", "Village do Lago I", "Rua Dois", 300);
//
//        Campus campus1 = new Campus();
//        campus1.setNome("Campus Montes Claros");
//        campus1.setEndereco(endereco1);
//        campus1.getPredios().add(predio1);
//        campus1.getPredios().add(predio2);
//
//        ControladorReservas reservas = new ControladorReservas(campus1);
//
//        Date data = new Date();
//        Funcionario funcionario = new Funcionario("Jefeson", "Professor", "123");
//        LocalTime horaInicio = LocalTime.parse("15:00:00");
//        LocalTime horaFim = LocalTime.parse("17:00:00");
//        // Sala sala1 = new Sala(1, 20);
//        
//        reservas.fazerNovaReserva(data, horaInicio, horaFim, sala2, funcionario);
//        
//        List<Sala> disponiveis = reservas.consultarSalaDisponivel(data, horaInicio, horaFim);
//        System.out.println(disponiveis);
//        //reservas.fazerNovaReserva(data, horaInicio, horaFim, sala1, funcionario);
//
//        //reservas.fazerNovaReserva(data, LocalTime.parse("05:18:23"), LocalTime.parse("06:18:23"), sala, funcionario);
    //}
    public static void fazerReserva(ControladorReservas controlador) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<Sala> salasDisponiveis = new ArrayList<>();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Informe dia que deseja realizar reserva: (dd/MM/yyyy)");
        String dataReservaString = sc.nextLine();
        Date dataReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataReservaString);
        //System.out.println("->" + dataReserva);

        System.out.println("Informe hora de inicio de reserva: (HH:mm)");
        String horaInicioString = sc.nextLine();
        LocalTime horaInicio = LocalTime.parse(horaInicioString, parser);
        //System.out.println("->" + horaInicio);

        System.out.println("Informe hora de inicio de reserva: (HH:mm)");
        String horaFimString = sc.nextLine();
        LocalTime horaFim = LocalTime.parse(horaFimString, parser);
        //System.out.println("->" + horaFim);
        salasDisponiveis = controlador.consultarSalaDisponivel(dataReserva, horaInicio, horaFim);

        if (salasDisponiveis == null) {
            System.out.println("Não há salas disponíveis em dia e horário informado.");

        } else {

            for (Sala s : salasDisponiveis) {
                System.out.println(s.toString());
            }
            System.out.println("Informa dentre salas acima, qual deseja realizar reserva: ");
            int salaNumero = sc.nextInt();
            



            
            

        }

    }

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        Sala sala1 = new Sala(1, 20);
        Sala sala2 = new Sala(2, 20);
        Sala sala3 = new Sala(3, 20);
        Sala sala4 = new Sala(4, 20);

        Predio predio1 = new Predio();
        predio1.setNome("Predio 1");
        predio1.getSalas().add(sala1);
        predio1.getSalas().add(sala2);
        predio1.getSalas().add(sala3);
        predio1.getSalas().add(sala4);

        Sala sala5 = new Sala(5, 20);
        Sala sala6 = new Sala(6, 20);
        Sala sala7 = new Sala(7, 20);
        Sala sala8 = new Sala(8, 20);

        Predio predio2 = new Predio();
        predio2.setNome("Predio 2");
        predio2.getSalas().add(sala5);
        predio2.getSalas().add(sala6);
        predio2.getSalas().add(sala7);
        predio2.getSalas().add(sala8);

        Endereco endereco1 = new Endereco("Montes Claros", "Village do Lago I", "Rua Dois", 300);

        Campus campus1 = new Campus();
        campus1.setNome("Campus Montes Claros");
        campus1.setEndereco(endereco1);
        campus1.getPredios().add(predio1);
        campus1.getPredios().add(predio2);

        Endereco endereco2 = new Endereco("Pirapora", "Santos Dumont", "Av. Humberto Mallard", 1355);

        Campus campus2 = new Campus();
        campus2.setNome("Campus Pirapora");
        campus2.setEndereco(endereco2);
        campus2.getPredios().add(predio1);
        campus2.getPredios().add(predio2);

        listaCampus.add(campus1);
        listaCampus.add(campus2);

        System.out.println("Bem vindo ao Sistema de Reserva de Salas!\n");

        int opcao;

        while (true) {

            System.out.println("Por favor informe campus que deseja realizar pesquisa:\n");

            for (Campus c : listaCampus) {
                System.out.println(c.getNome());
            }

            System.out.println("Digite: ");
            String campusNome = sc.nextLine();

            Campus campusSelecionado = new Campus();

            campusSelecionado = pesquisarCampus(campusNome);

            while (campusSelecionado == null) {

                System.out.println("Digite nome de campus válido: ");
                campusNome = sc.nextLine();
                campusSelecionado = pesquisarCampus(campusNome);
            }

            ControladorReservas controlador = new ControladorReservas(campusSelecionado);

            System.out.println(campusSelecionado.toString());

            menu();

            System.out.println("Opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:

                    fazerReserva(controlador);
                    break;

            }

            break;

        }

        /*
        
        
        //System.out.println(predio.getSalasDisponiveis());
        ControladorReservas reservas = new ControladorReservas();

        Date data = new Date();
        Funcionario funcionario = new Funcionario("Jefeson", "Professor", "123");
        LocalTime horaInicio = LocalTime.parse("15:00:00");
        LocalTime horaFim = LocalTime.parse("17:00:00");
        reservas.fazerNovaReserva(data, horaInicio, horaFim, sala1, funcionario);

        //reservas.fazerNovaReserva(data, LocalTime.parse("05:18:23"), LocalTime.parse("06:18:23"), sala, funcionario);
        //List<Reserva> r = reservas.getReservas();
        //System.out.println(reservas.getReservas());
        LocalTime horaInicio2 = LocalTime.parse("16:00:01");
        LocalTime horaFim2 = LocalTime.parse("16:30:00");
//
        List<Reserva> r = reservas.consultarSalaIndisponivel(data, horaInicio2, horaFim2);
        System.out.println(r);

        //List<Reserva> ri = reservas.consultarSalaDisponivel(data, horaInicio, horaFim);
        //System.out.println(ri);
//         LocalTime horaInicio2 = LocalTime.parse("23:59:59");
//         System.out.println(horaInicio2.toSecondOfDay());
    
         
        
         */
    }
}
