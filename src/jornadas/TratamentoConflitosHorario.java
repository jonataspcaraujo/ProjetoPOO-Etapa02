package jornadas;

import repositorio.ClinicaRepositorio;

public class TratamentoConflitosHorario {
    private ClinicaRepositorio repositorio;

    public TratamentoConflitosHorario(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nTRATAMENTO DE CONFLITOS DE HORARIOS");
        System.out.println("\n");
    }
}