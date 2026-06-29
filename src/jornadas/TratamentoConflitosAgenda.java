package jornadas;

import repositorio.ClinicaRepositorio;

public class TratamentoConflitosAgenda {
    private ClinicaRepositorio repositorio;

    public TratamentoConflitosAgenda(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nTRATAMENTO DE CONFLITOS DE AGENDA");
        System.out.println("\n");
    }
}