package relatorios;

import repositorio.ClinicaRepositorio;
import model.*;
import java.util.List;
import java.util.ArrayList;

public class ExportacaoDadosOperacionais {
    private ClinicaRepositorio repositorio;

    public ExportacaoDadosOperacionais(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nEXPORTACAO DE DADOS OPERACIONAIS");
        System.out.println("\n");

        List<Consulta> consultas = repositorio.getConsultas();
        List<Paciente> pacientes = new ArrayList<>(repositorio.getMapaPacientes().values());
        List<Profissional> profissionais = new ArrayList<>(repositorio.getMapaProfissionais().values());
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-20s | %-15s | %-15s | %-10s\n", "TIPO", "NOME", "CPF", "STATUS/TIPO", "INFO EXTRA");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (Paciente p : pacientes) {
            String conv = p.getConvenio() != null ? "Conv: " + p.getConvenio().getNome() : "Particular";
            System.out.printf("%-15s | %-20s | %-15s | %-15s | %-10s\n", "Paciente", p.getNome(), p.getCpf(), p.isAtivo() ? "Ativo" : "Inativo", conv);
        }

        for (Profissional p : profissionais) {
            System.out.printf("%-15s | %-20s | %-15s | %-15s | %-10s\n", "Profissional", p.getNome(), p.getCpf(), p.getEspecialidade(), "R$" + p.getValorConsulta());
        }

        for (Consulta c : consultas) {
            System.out.printf("%-15s | %-20s | %-15s | %-15s | %-10s\n", "Consulta", c.getNomeProfissional(), c.getCpfPaciente(), c.getStatus(), c.getData() + " " + c.getHorario());
        }
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Total de Pacientes: " + pacientes.size());
        System.out.println("Total de Profissionais: " + profissionais.size());
        System.out.println("Total de Consultas: " + consultas.size());
    }
}