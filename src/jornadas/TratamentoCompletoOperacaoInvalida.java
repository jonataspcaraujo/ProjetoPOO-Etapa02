package jornadas;

import repositorio.ClinicaRepositorio;
import java.util.Scanner;

public class TratamentoCompletoOperacaoInvalida {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public TratamentoCompletoOperacaoInvalida(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nTRATAMENTO COMPLETO DE OPERACOES INVALIDAS");
        System.out.println("\n");
    }
}
