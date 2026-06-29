package relatorios;

import repositorio.ClinicaRepositorio;
import model.*;

public class RelatorioUnificadoCadastro {
    private ClinicaRepositorio repositorio;

    public RelatorioUnificadoCadastro(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nRELATORIO UNIFICADO DE CADASTROS");
        int totalPacientes = 0, totalProfissionais = 0;
        
        for (Pessoa p : repositorio.getTodasAsPessoas()) {
            System.out.println(p.exibirResumo());
            if (p instanceof Paciente) {
                Paciente pac = (Paciente) p;
                if (pac.getConvenio() != null) System.out.println("  >> Convenio: " + pac.getConvenio().getNome());
                totalPacientes++;
            } else if (p instanceof Profissional) {
                Profissional prof = (Profissional) p;
                System.out.println("  >> Especialidade: " + prof.getEspecialidade());
                totalProfissionais++;
            }
            System.out.println();
        }
        System.out.println("Total de pacientes: " + totalPacientes);
        System.out.println("Total de profissionais: " + totalProfissionais);
    }
}