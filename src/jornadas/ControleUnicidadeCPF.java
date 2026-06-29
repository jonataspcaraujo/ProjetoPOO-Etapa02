package jornadas;

import repositorio.ClinicaRepositorio;

public class ControleUnicidadeCPF {
    private ClinicaRepositorio repositorio;

    public ControleUnicidadeCPF(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCONTROLE DE UNICIDADE DE CPF");
        System.out.println("\n");
    }
}