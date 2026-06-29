package jornadas;

import repositorio.ClinicaRepositorio;

public class CadastroValidacaoRobustaDados {
    private ClinicaRepositorio repositorio;

    public CadastroValidacaoRobustaDados(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO DE DADOS COM VALIDACAO ROBUSTA");
        System.out.println("\n");
    }
}