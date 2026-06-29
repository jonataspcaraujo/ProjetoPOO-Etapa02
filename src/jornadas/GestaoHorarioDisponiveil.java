package jornadas;

import repositorio.ClinicaRepositorio;

public class GestaoHorarioDisponiveil {
    private ClinicaRepositorio repositorio;

    public GestaoHorarioDisponiveil(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nGESTAO DE HORARIOS DISPONIVEIS");
        System.out.println("\n");
    }
}