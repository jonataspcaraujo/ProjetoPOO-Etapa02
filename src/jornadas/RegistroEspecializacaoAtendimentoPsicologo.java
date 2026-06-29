package jornadas;

import repositorio.ClinicaRepositorio;

public class RegistroEspecializacaoAtendimentoPsicologo {
    private ClinicaRepositorio repositorio;

    public RegistroEspecializacaoAtendimentoPsicologo(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nREGISTRO ESPECIALIZADO EM ATENDIMENTO PSICOLOGO");
        System.out.println("\n");
    }
}
