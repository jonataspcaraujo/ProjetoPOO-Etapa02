package jornadas;

import repositorio.ClinicaRepositorio;

public class VerificacaoCoberturaConvenio {
    private ClinicaRepositorio repositorio;

    public VerificacaoCoberturaConvenio(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nVERIFICACAO COBERTURA CONVENIO");
        System.out.println("\n");
    }
}