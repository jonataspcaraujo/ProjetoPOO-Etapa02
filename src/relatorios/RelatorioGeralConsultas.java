package relatorios;

import repositorio.ClinicaRepositorio;
import model.*;

public class RelatorioGeralConsultas {
    private ClinicaRepositorio repositorio;

    public RelatorioGeralConsultas(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nRELATORIO GERAL DE CONSULTAS");
        
        if (repositorio.getConsultas().isEmpty()) {
            System.out.println("Nenhuma consulta registrada.");
            return;
        }
        for (int i = 0; i < repositorio.getConsultas().size(); i++) {
            System.out.println("[" + i + "] " + repositorio.getConsultas().get(i).exibirResumo());
        }
    }
}
