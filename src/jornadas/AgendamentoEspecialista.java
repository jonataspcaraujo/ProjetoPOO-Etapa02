package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import java.util.Scanner;

public class AgendamentoEspecialista {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public AgendamentoEspecialista(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nAGENDAR CONSULTA POR ESPECIALIDADE");
        System.out.println("\n");
    }
}