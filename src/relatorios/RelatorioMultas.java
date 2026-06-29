package relatorios;

import repositorio.ClinicaRepositorio;

public class RelatorioMultas {
    private ClinicaRepositorio repositorio;

    public RelatorioMultas(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nRELATORIO DE MULTAS");
        
        if (repositorio.getMultas().isEmpty()) {
            System.out.println("Nenhuma multa registrada.");
            return;
        }
        double total = 0;

        for (int i = 0; i < repositorio.getMultas().size(); i++) {
            System.out.println("Multa " + (i + 1) + ": R$" + repositorio.getMultas().get(i));
            total += repositorio.getMultas().get(i);
        }
        System.out.println("Total: R$" + Math.round(total * 100.0) / 100.0);
    }
}