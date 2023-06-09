/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.bancodedados;

import br.edu.ifnmg.bookaroom.campussala.CampusSala;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.endereco.Endereco;
import br.edu.ifnmg.bookaroom.equipamento.Equipamento;
import br.edu.ifnmg.bookaroom.funcionario.Funcionario;
import br.edu.ifnmg.bookaroom.predio.Predio;
import br.edu.ifnmg.bookaroom.reservas.Reserva;
import br.edu.ifnmg.bookaroom.sala.Sala;

/**
 *
 * @author dayan
 */
public class BancoDeDados {
    private static BancoDeDados instancia;

    private BancoDeDados() {
        this.iniciarObjetos();
    }

    public static BancoDeDados getInstance() {
        if (instancia == null) {
            synchronized (BancoDeDados.class) {
                if (instancia == null) {
                    instancia = new BancoDeDados();
                }
            }
        }
        return instancia;
    }

    private List<Campus> listaCampus = new ArrayList<>();
    private List<CampusSala> listaCampusSalas = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();

    public List<Reserva> getReserva() {
        return listaReservas;
    }

    public void addReserva(Reserva reserva) {
        listaReservas.add(reserva);
    }

    public List<Campus> getListaCampus() {
        return listaCampus;
    }

    public List<Sala> getListaSalas(Campus campus) {
        List<Sala> salas = new ArrayList<>();
        for (CampusSala cs : listaCampusSalas) {
            salas.add(cs.getSala());
        }
        return salas;
    }

    private void iniciarObjetos() {

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

        Equipamento equipamento1 = new Equipamento( "Lousa Digital 1", "IFNMG");
        Equipamento equipamento2 = new Equipamento( "Lousa Digital 2", "IFNMG");
        Equipamento equipamento3 = new Equipamento( "Notebook Dell", "IFNMG");
        Equipamento equipamento4 = new Equipamento( "Notebook Acer", "IFNMG");
        Equipamento equipamento5 = new Equipamento( "Projetor 1", "IFNMG");
        Equipamento equipamento6 = new Equipamento( "Projetor 2", "IFNMG");

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
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="CAMPUS PIRAPORA">
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

        Equipamento equipamento7 = new Equipamento("Lousa Digital 1", "IFNMG");
        Equipamento equipamento8 = new Equipamento("Lousa Digital 2", "IFNMG");
        Equipamento equipamento9 = new Equipamento("Notebook Asus", "IFNMG");
        Equipamento equipamento10 = new Equipamento("Notebook Lenovo", "IFNMG");
        Equipamento equipamento11 = new Equipamento("Projetor 1", "IFNMG");
        Equipamento equipamento12 = new Equipamento("Projetor 2", "IFNMG");

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
        // </editor-fold>
        listaCampus.add(campus1);
        listaCampus.add(campus2);
        for (Campus c : listaCampus) {
            for (Predio p : c.getPredios()) {
                for (Sala s : p.getSalas()) {
                    listaCampusSalas.add(new CampusSala(c, s));
                }
            }
        }

    }

    public void definirHorarioAulas(Campus campus) throws ParseException {
        Date inicioSemestre;
        Date fimSemestre;

        inicioSemestre = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2023");
        fimSemestre = new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2023");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicioSemestre);

        while (calendar.getTime().before(fimSemestre)) {
            // faça o que precisa ser feito em cada data do intervalo
            // por exemplo, imprimir a data
            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            //System.out.println(inicioSemestre);
            if (diaSemana != Calendar.SATURDAY && diaSemana != Calendar.SUNDAY) {
                // Imprime a data
               // System.out.println(calendar.getTime());

                for (Predio predio : campus.getPredios()) {
                    for (Sala sala : predio.getSalas()) {
                        Reserva reserva = new Reserva(campus);
                        Date dataAlocacao = calendar.getTime();
                        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
                        LocalTime horaInicio = LocalTime.parse("07:20", parser);
                        LocalTime horaFim = LocalTime.parse("12:40", parser);
                        Funcionario autorReserva = new Funcionario("RESERVADO_AULA", "RESERVADO_AULA",
                                "RESERVADO_AULA");

                        reserva.setDataAlocacao(dataAlocacao);
                        reserva.setHoraInicio(horaInicio);
                        reserva.setHoraFim(horaFim);
                        reserva.setAutorReserva(autorReserva);
                        reserva.setSala(sala);
                        reserva.setCampus(campus);

                        Reserva reserva2 = new Reserva(campus);
                        Date dataAlocacao2 = calendar.getTime();
                        DateTimeFormatter parser2 = DateTimeFormatter.ofPattern("HH:mm");
                        LocalTime horaInicio2 = LocalTime.parse("13:20", parser2);
                        LocalTime horaFim2 = LocalTime.parse("18:40", parser2);
                        Funcionario autorReserva2 = new Funcionario("RESERVADO_AULA", "RESERVADO_AULA",
                                "RESERVADO_AULA");

                        reserva2.setDataAlocacao(dataAlocacao2);
                        reserva2.setHoraInicio(horaInicio2);
                        reserva2.setHoraFim(horaFim2);
                        reserva2.setAutorReserva(autorReserva2);
                        reserva2.setSala(sala);
                        reserva2.setCampus(campus);

                        addReserva(reserva);
                        addReserva(reserva2);
                    }
                }

            }

            // avança para a próxima data
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

//        for(Reserva reserva: listaReservas){
//            System.out.println(reserva.getAutorReserva());
//            System.out.println(listaReservas.size());
//        }

    }

}
