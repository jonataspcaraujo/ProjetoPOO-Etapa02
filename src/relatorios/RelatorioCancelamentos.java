package relatorios;

import repositorio.ClinicaRepositorio;
import model.*;

public class RelatorioCancelamentos {
    private ClinicaRepositorio repositorio;

    public RelatorioCancelamentos(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nRELATORIO DE CANCELAMENTOS");
        boolean achou = false;
        
        for (int i = 0; i < repositorio.getConsultas().size(); i++) {
            Consulta c = repositorio.getConsultas().get(i);
            if (c.getStatus().equals("cancelada")) {
                System.out.println("[" + i + "] " + c.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum cancelamento registrado.");
    }
}
