package jornadas;

import repositorio.ClinicaRepositorio;

public class ProcessamentoPagamentoDinheiro {
    private ClinicaRepositorio repositorio;

    public ProcessamentoPagamentoDinheiro(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nPROCESSAMENTO DE PAGAMENTO EM DINHEIRO");
        System.out.println("\n");
    }
}
