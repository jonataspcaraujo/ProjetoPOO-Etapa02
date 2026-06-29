package jornadas;

import repositorio.ClinicaRepositorio;

public class CadastroPacienteControleDuplicidade {
    private ClinicaRepositorio repositorio;

    public CadastroPacienteControleDuplicidade(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO DE PACIENTE COM CONTROLE DE DUPLICIDADE");
        System.out.println("\n");
    }
}
