package jornadas;

import repositorio.ClinicaRepositorio;

public class ProcessamentoPagamentoConvenio {
    private ClinicaRepositorio repositorio;

    public ProcessamentoPagamentoConvenio(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nPROCESSAMENTO DE PAGAMENTO POR CONVÊNIO");
        System.out.println("\n");
    }
}
