package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class AgendamentoConsultaProfissional {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public AgendamentoConsultaProfissional(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nAGENDAR CONSULTA POR PROFISSIONAL");
        System.out.print("CPF do Paciente: ");
        String cpf = sc.nextLine();
        
        try {
            Paciente paciente = repositorio.buscarPaciente(cpf);
            if (!paciente.isAtivo()) {
                System.out.println("Erro: Paciente inativo.");
                return;
            }
            
            System.out.print("Nome do Profissional: ");
            String nomeProf = sc.nextLine();
            Profissional prof = repositorio.buscarProfissional(nomeProf);
            
            System.out.print("Data (DD/MM/AAAA): ");
            String data = sc.nextLine();
            
            System.out.print("Horario (HH:MM): ");
            String horario = sc.nextLine();
            
            System.out.print("Tipo (Inicial/Retorno): ");
            String tipo = sc.nextLine();
            
            for (Consulta c : repositorio.getConsultas()) {
                if (c.getNomeProfissional().equals(nomeProf) && c.getData().equals(data) && c.getHorario().equals(horario) && c.getStatus().equals("agendada")) {
                    throw new HorarioIndisponivelException("Horario indisponivel para este profissional.");
                }
            }

            Consulta consulta = new Consulta(cpf, nomeProf, data, horario, tipo);
            repositorio.getConsultas().add(consulta);
            System.out.println("Consulta agendada com sucesso!");
            
        } catch (PacienteNaoEncontradoException | ProfissionalNaoEncontradoException | HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
