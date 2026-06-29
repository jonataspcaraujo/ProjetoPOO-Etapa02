package jornadas;

import repositorio.ClinicaRepositorio;

public class ProcessamentoPagamentoCartao {
    private ClinicaRepositorio repositorio;

    public ProcessamentoPagamentoCartao(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nPROCESSAMENTO DE PAGAMENTO POR CARTAO");
        System.out.println("\n");
    }
}
