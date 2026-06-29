package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class DesativacaoProfissional {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public DesativacaoProfissional(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nDESATIVAR PROFISSIONAL");
        System.out.print("Nome do Profissional: ");
        try {
            Profissional p = repositorio.buscarProfissional(sc.nextLine());
            System.out.println("Desativacao não implementada.");
            System.out.println("Operacao concluida.");
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}