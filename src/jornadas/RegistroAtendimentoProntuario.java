package jornadas;

import repositorio.ClinicaRepositorio;

public class RegistroAtendimentoProntuario {
    private ClinicaRepositorio repositorio;

    public RegistroAtendimentoProntuario(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nREGISTRO DE ATENDIMENTO EM PRONTUARIO");
        System.out.println("\n");
    }
}
