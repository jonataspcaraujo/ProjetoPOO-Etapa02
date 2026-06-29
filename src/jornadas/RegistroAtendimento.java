package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroAtendimento {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public RegistroAtendimento(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nREGISTRAR ATENDIMENTO");
        System.out.print("Indice da Consulta (0, 1, 2...): ");
        int indice;
        try { indice = Integer.parseInt(sc.nextLine()); } catch (Exception e) { System.out.println("Indice invalido."); return; }
        
        try {
            Consulta c = repositorio.getConsulta(indice);
            if (!c.getStatus().equals("agendada")) {
                throw new OperacaoInvalidaException("Apenas consultas agendadas podem ter atendimento registrado.");
            }
            
            System.out.print("Observacoes: ");
            String obs = sc.nextLine();
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();
            
            String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Atendimento at = new Atendimento(indice, obs, diag, dataHoje);
            
            repositorio.getAtendimentos().add(at);
            c.realizar();
            System.out.println("Atendimento registrado com sucesso!");
            
        } catch (ConsultaNaoEncontradaException | OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
