package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class RemarcacaoConsulta {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public RemarcacaoConsulta(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nREMARCAR CONSULTA");
        System.out.print("Indice da Consulta Original: ");
        int indice;
        try { indice = Integer.parseInt(sc.nextLine()); } catch (Exception e) { System.out.println("Indice invalido."); return; }
        
        try {
            Consulta c = repositorio.getConsulta(indice);
            if (!c.getStatus().equals("agendada")) throw new OperacaoInvalidaException("So e possivel remarcar consultas agendadas.");
            
            System.out.print("Nova Data (DD/MM/AAAA): ");
            String novaData = sc.nextLine();
            System.out.print("Novo Horario (HH:MM): ");
            String novoHorario = sc.nextLine();
            
            c.remarcar();
            Consulta nova = new Consulta(c.getCpfPaciente(), c.getNomeProfissional(), novaData, novoHorario, c.getTipo(), "agendada");
            repositorio.getConsultas().add(nova);
            System.out.println("Consulta remarcada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}