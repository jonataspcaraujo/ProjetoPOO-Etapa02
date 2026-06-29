package jornadas;

import repositorio.ClinicaRepositorio;

public class AgendamentoPacienteInativo {
    private ClinicaRepositorio repositorio;

    public AgendamentoPacienteInativo(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nAGENDAMENTO DE PACIENTES INATIVOS");
        System.out.println("\n");
    }
}